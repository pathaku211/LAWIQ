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

class MunicipalitiesFragment : Fragment(), ArticlesAdapter.OnArticleClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ArticlesAdapter
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val articles = mutableListOf<Article>()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_municipalities, container, false)

        // Initialize RecyclerView and Adapter
        recyclerView = view.findViewById(R.id.recyclerViewMunicipalities)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ArticlesAdapter(articles, this)
        recyclerView.adapter = adapter

        // Load article titles
        initializeArticles()

        return view
    }

    private fun initializeArticles() {
        // Preload articles for Municipalities (Articles 243-P to 243-Z-G)
        articles.add(Article("Article 243-P", "", "Author Name"))
        articles.add(Article("Article 243-Q", "", "Author Name"))
        articles.add(Article("Article 243-R", "", "Author Name"))
        articles.add(Article("Article 243-S", "", "Author Name"))
        articles.add(Article("Article 243-T", "", "Author Name"))
        articles.add(Article("Article 243-U", "", "Author Name"))
        articles.add(Article("Article 243-V", "", "Author Name"))
        articles.add(Article("Article 243-W", "", "Author Name"))
        articles.add(Article("Article 243-X", "", "Author Name"))
        articles.add(Article("Article 243-Y", "", "Author Name"))
        articles.add(Article("Article 243-Z", "", "Author Name"))
        articles.add(Article("Article 243-Z-A", "", "Author Name"))
        articles.add(Article("Article 243-Z-B", "", "Author Name"))
        articles.add(Article("Article 243-Z-C", "", "Author Name"))
        articles.add(Article("Article 243-Z-D", "", "Author Name"))
        articles.add(Article("Article 243-Z-E", "", "Author Name"))
        articles.add(Article("Article 243-Z-F", "", "Author Name"))
        articles.add(Article("Article 243-Z-G", "", "Author Name"))

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
