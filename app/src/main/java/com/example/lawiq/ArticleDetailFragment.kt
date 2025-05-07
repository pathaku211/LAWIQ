package com.example.lawiq

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.DocumentSnapshot

class ArticleDetailFragment : Fragment() {

    private lateinit var articleTitleTextView: TextView
    private lateinit var articleDescriptionTextView: TextView
    private lateinit var articleImage: ImageView
    private val db = FirebaseFirestore.getInstance()  // Firebase Firestore instance

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the fragment layout
        val view = inflater.inflate(R.layout.fragment_article_detail, container, false)

        // Initialize views
        articleTitleTextView = view.findViewById(R.id.articleTitleTextView)
        articleDescriptionTextView = view.findViewById(R.id.articleDescriptionTextView)
        articleImage = view.findViewById(R.id.imageView10)

        // Fetch data from Firestore
        fetchArticleData()

        return view
    }

    private fun fetchArticleData() {
        // Get the document reference for Article 52 (adjust the collection and document name)
        db.collection("Article")  // Assuming the collection is named "Article"
            .document("Article 52")  // Document name is "Article 52"
            .get()
            .addOnSuccessListener { documentSnapshot ->
                // Check if the document exists
                if (documentSnapshot.exists()) {
                    // Get data from Firestore document
                    val title = documentSnapshot.getString("title") ?: "Title not available"
                    val description = documentSnapshot.getString("description") ?: "Description not available"
                    val imageUrl = documentSnapshot.getString("imageURL")  // Assuming imageURL is a field in Firestore

                    // Set data to views
                    articleTitleTextView.text = title
                    articleDescriptionTextView.text = description

                    // Load the image using Glide (if image URL exists)
                    imageUrl?.let { url ->
                        Glide.with(this)
                            .load(url)
                            .into(articleImage)
                    }
                }
            }
            .addOnFailureListener { exception ->
                // Handle the error
                articleTitleTextView.text = "Error: ${exception.message}"
            }
    }
}
