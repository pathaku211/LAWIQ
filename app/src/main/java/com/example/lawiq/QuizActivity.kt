package com.example.lawiq

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.lawiq.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

class QuizActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var submitButton: Button

    private val client = OkHttpClient()
    private val questionsList = mutableListOf<TriviaQuestion>()
    private var currentQuestionIndex = 0
    private var currentScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // Initialize views
        questionTextView = findViewById(R.id.questionText)
        radioGroup = findViewById(R.id.optionsGroup)
        submitButton = findViewById(R.id.submitButton1)

        // Fetch questions from API using Coroutine
        fetchTriviaQuestions()

        // Setup submit button click
        submitButton.setOnClickListener {
            submitAnswer()
        }
    }

    private fun fetchTriviaQuestions() {
        lifecycleScope.launch {
            // Run network request in background thread
            val triviaQuestions = fetchTriviaQuestionsFromAPI()

            // Update UI on the main thread with the fetched questions
            if (triviaQuestions.isNotEmpty()) {
                questionsList.clear()
                questionsList.addAll(triviaQuestions)
                displayQuestion(questionsList[0])
            }
        }
    }

    private suspend fun fetchTriviaQuestionsFromAPI(): List<TriviaQuestion> {
        return withContext(Dispatchers.IO) {
            val url = "https://opentdb.com/api.php?amount=5&type=multiple"
            val request = Request.Builder().url(url).build()

            try {
                val response = client.newCall(request).execute()
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                val jsonResponse = JSONObject(response.body?.string())
                val results = jsonResponse.getJSONArray("results")

                val triviaQuestions = mutableListOf<TriviaQuestion>()
                for (i in 0 until results.length()) {
                    val questionObject = results.getJSONObject(i)
                    val question = questionObject.getString("question")
                    val correctAnswer = questionObject.getString("correct_answer")
                    val incorrectAnswers = questionObject.getJSONArray("incorrect_answers")

                    // Add the question to the list
                    val options = mutableListOf<String>()
                    options.add(correctAnswer)
                    for (j in 0 until incorrectAnswers.length()) {
                        options.add(incorrectAnswers.getString(j))
                    }

                    // Randomly shuffle the answers
                    options.shuffle()

                    val triviaQuestion = TriviaQuestion(question, options, correctAnswer)
                    triviaQuestions.add(triviaQuestion)
                }
                triviaQuestions
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList<TriviaQuestion>()
            }
        }
    }

    private fun displayQuestion(question: TriviaQuestion) {
        // Set the question text
        questionTextView.text = question.question

        // Clear previous radio buttons
        radioGroup.removeAllViews()

        // Add options as radio buttons
        for (option in question.options) {
            val radioButton = RadioButton(this)
            radioButton.text = option
            radioGroup.addView(radioButton)
        }
    }

    private fun submitAnswer() {
        // Get the selected radio button
        val selectedRadioButtonId = radioGroup.checkedRadioButtonId
        if (selectedRadioButtonId == -1) {
            // No answer selected
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
            return
        }

        // Find the selected radio button
        val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)

        // Check if the selected answer is correct
        val currentQuestion = questionsList[currentQuestionIndex]
        if (selectedRadioButton.text == currentQuestion.correctAnswer) {
            currentScore++
        }

        // Move to the next question
        currentQuestionIndex++

        if (currentQuestionIndex < questionsList.size) {
            // Show the next question
            displayQuestion(questionsList[currentQuestionIndex])
        } else {
            // Quiz completed, show the final score
            showFinalScore()
        }
    }

    private fun showFinalScore() {
        // Show a toast with the final score
        Toast.makeText(this, "Quiz Completed! Your score is: $currentScore", Toast.LENGTH_LONG).show()

        // Optionally, you can finish the activity or reset the quiz
        // finish()  // Finish the activity after the quiz
    }
}

data class TriviaQuestion(val question: String, val options: List<String>, val correctAnswer: String)
