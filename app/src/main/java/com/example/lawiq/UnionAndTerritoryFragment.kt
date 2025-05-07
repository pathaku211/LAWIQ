package com.example.lawiq

import Article
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class UnionAndTerritoryFragment : Fragment(), ArticlesAdapter.OnArticleClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ArticlesAdapter
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val articles = mutableListOf<Article>()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_union_and_territory, container, false)

        // Initialize RecyclerView and Adapter
        recyclerView = view.findViewById(R.id.recyclerViewArticles)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ArticlesAdapter(articles, this)
        recyclerView.adapter = adapter

        // Load article titles
        initializeArticles()

        return view
    }

    private fun initializeArticles() {
        // Preload articles with titles and empty content
        for (i in 1..4) {
            articles.add(Article("Article $i", "", "Author Name"))
        }

        // Notify adapter after adding data
        adapter.notifyDataSetChanged()
    }

    override fun onArticleClick(article: Article) {
        // Fetch the detailed data from Firestore when an article is clicked
        val docRef = db.collection("Article").document(article.title)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val description = document.getString("description") ?: "No description available"
                    val image = document.getString("image") ?: ""

                    Log.d("Database", "Fetched Description: $description")

                    // Update the clicked article's content and image URL
                    article.content = description
                    article.imageURL = image

                    // Open the article detail fragment
                    openArticleDetailFragment(article)
                } else {
                    Log.d("Database", "No such document exists for ${article.title}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Database", "Failed to fetch data for ${article.title}", exception)
            }
    }

    private fun openArticleDetailFragment(article: Article) {
        // Create an instance of ArticleDetailFragment
        val detailFragment = ArticleDetailFragment()

        // Bundle the article data to pass to the detail fragment
        val bundle = Bundle().apply {
            putParcelable("article", article) // Parcelable is used to pass the article object
        }
        detailFragment.arguments = bundle

        // Replace the current fragment with the detail fragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailFragment)
            .addToBackStack(null)
            .commit()
    }
}
