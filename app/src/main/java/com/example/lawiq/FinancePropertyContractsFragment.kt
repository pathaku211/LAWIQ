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

class FinancePropertyContractsFragment : Fragment(), ArticlesAdapter.OnArticleClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ArticlesAdapter
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val articles = mutableListOf<Article>()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_finance_property_contracts, container, false)

        // Initialize RecyclerView and Adapter
        recyclerView = view.findViewById(R.id.recyclerViewFinancePropertyContracts)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ArticlesAdapter(articles, this)
        recyclerView.adapter = adapter

        // Load article titles
        initializeArticles()

        return view
    }

    private fun initializeArticles() {
        // Preload articles for Finance, Property, Contracts and Suits (Articles 264 to 300-A)
        articles.add(Article("Article 264", "", "Author Name"))
        articles.add(Article("Article 265", "", "Author Name"))
        articles.add(Article("Article 266", "", "Author Name"))
        articles.add(Article("Article 267", "", "Author Name"))
        articles.add(Article("Article 268", "", "Author Name"))
        articles.add(Article("Article 269", "", "Author Name"))
        articles.add(Article("Article 270", "", "Author Name"))
        articles.add(Article("Article 271", "", "Author Name"))
        articles.add(Article("Article 272", "", "Author Name"))
        articles.add(Article("Article 273", "", "Author Name"))
        articles.add(Article("Article 274", "", "Author Name"))
        articles.add(Article("Article 275", "", "Author Name"))
        articles.add(Article("Article 276", "", "Author Name"))
        articles.add(Article("Article 277", "", "Author Name"))
        articles.add(Article("Article 278", "", "Author Name"))
        articles.add(Article("Article 279", "", "Author Name"))
        articles.add(Article("Article 280", "", "Author Name"))
        articles.add(Article("Article 281", "", "Author Name"))
        articles.add(Article("Article 282", "", "Author Name"))
        articles.add(Article("Article 283", "", "Author Name"))
        articles.add(Article("Article 284", "", "Author Name"))
        articles.add(Article("Article 285", "", "Author Name"))
        articles.add(Article("Article 286", "", "Author Name"))
        articles.add(Article("Article 287", "", "Author Name"))
        articles.add(Article("Article 288", "", "Author Name"))
        articles.add(Article("Article 289", "", "Author Name"))
        articles.add(Article("Article 290", "", "Author Name"))
        articles.add(Article("Article 291", "", "Author Name"))
        articles.add(Article("Article 292", "", "Author Name"))
        articles.add(Article("Article 293", "", "Author Name"))
        articles.add(Article("Article 294", "", "Author Name"))
        articles.add(Article("Article 295", "", "Author Name"))
        articles.add(Article("Article 296", "", "Author Name"))
        articles.add(Article("Article 297", "", "Author Name"))
        articles.add(Article("Article 298", "", "Author Name"))
        articles.add(Article("Article 299", "", "Author Name"))
        articles.add(Article("Article 300", "", "Author Name"))
        articles.add(Article("Article 300-A", "", "Author Name"))

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
