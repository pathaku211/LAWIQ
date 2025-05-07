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

class PanchayatsFragment : Fragment(), ArticlesAdapter.OnArticleClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ArticlesAdapter
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val articles = mutableListOf<Article>()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_panchayats, container, false)

        // Initialize RecyclerView and Adapter
        recyclerView = view.findViewById(R.id.recyclerViewPanchayats)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ArticlesAdapter(articles, this)
        recyclerView.adapter = adapter

        // Load article titles
        initializeArticles()

        return view
    }

    private fun initializeArticles() {
        // Preload articles for Panchayats (Articles 243-A to 243-O)
        articles.add(Article("Article 243-A", "", "Author Name"))
        articles.add(Article("Article 243-B", "", "Author Name"))
        articles.add(Article("Article 243-C", "", "Author Name"))
        articles.add(Article("Article 243-D", "", "Author Name"))
        articles.add(Article("Article 243-E", "", "Author Name"))
        articles.add(Article("Article 243-F", "", "Author Name"))
        articles.add(Article("Article 243-G", "", "Author Name"))
        articles.add(Article("Article 243-H", "", "Author Name"))
        articles.add(Article("Article 243-I", "", "Author Name"))
        articles.add(Article("Article 243-J", "", "Author Name"))
        articles.add(Article("Article 243-K", "", "Author Name"))
        articles.add(Article("Article 243-L", "", "Author Name"))
        articles.add(Article("Article 243-M", "", "Author Name"))
        articles.add(Article("Article 243-N", "", "Author Name"))
        articles.add(Article("Article 243-O", "", "Author Name"))

        // Notify adapter after adding data
        adapter.notifyDataSetChanged()
    }

    override fun onArticleClick(article: Article) {
        val docRef = db.collection("Article").document(article.title)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val description = document.getString("description") ?: "No description available"
                    val image = document.getString("image") ?: ""

                    Log.d("Database", "Fetched Description: $description")

                    article.content = description
                    article.imageURL = image

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
        val detailFragment = ArticleDetailFragment()
        val bundle = Bundle().apply {
            putParcelable("article", article)
        }
        detailFragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailFragment)
            .addToBackStack(null)
            .commit()
    }
}
