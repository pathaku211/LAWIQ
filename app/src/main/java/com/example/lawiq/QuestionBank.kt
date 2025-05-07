package com.example.lawiq.data

import com.example.lawiq.models.QuizQuestion

object QuestionBank {

    fun getQuestionsForSection(sectionName: String): List<QuizQuestion> {
        return when (sectionName) {
            "Union and its Territory (Articles 1 - 4)" -> listOf(
                QuizQuestion(
                    "What does Article 1 of the Indian Constitution define?",
                    listOf("States", "Territory of India", "Languages", "Fundamental Rights"),
                    1
                ),
                QuizQuestion(
                    "Which Article deals with the admission or establishment of new States?",
                    listOf("Article 2", "Article 3", "Article 4", "Article 1"),
                    0
                ),
                QuizQuestion(
                    "Which Article empowers Parliament to form a new state?",
                    listOf("Article 4", "Article 3", "Article 2", "Article 5"),
                    1
                ),
                // Add 7 more...
            )

            "Citizenship (Articles 5 – 11)" -> listOf(
                QuizQuestion(
                    "Who is considered a citizen at the commencement of the Constitution?",
                    listOf("Article 7", "Article 6", "Article 5", "Article 10"),
                    2
                ),
                QuizQuestion(
                    "Article 10 guarantees what to citizens?",
                    listOf("Right to vote", "Right to property", "Continuation of citizenship", "Domicile rights"),
                    2
                ),
                // Add more...
            )

            // Repeat this for all 24 sections

            else -> emptyList()
        }
    }
}
