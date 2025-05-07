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

class RelationsUnionStatesFragment : Fragment(), ArticlesAdapter.OnArticleClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ArticlesAdapter
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val articles = mutableListOf<Article>()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_relations_union_states, container, false)

        // Initialize RecyclerView and Adapter
        recyclerView = view.findViewById(R.id.recyclerViewRelationsUnionStates)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ArticlesAdapter(articles, this)
        recyclerView.adapter = adapter

        // Load article titles
        initializeArticles()

        return view
    }

    private fun initializeArticles() {
        // Preload articles for Relations between Union and the States (Articles 245 to 263)
        articles.add(Article("Article 245", "", "Author Name"))
        articles.add(Article("Article 246", "", "Author Name"))
        articles.add(Article("Article 247", "", "Author Name"))
        articles.add(Article("Article 248", "", "Author Name"))
        articles.add(Article("Article 249", "", "Author Name"))
        articles.add(Article("Article 250", "", "Author Name"))
        articles.add(Article("Article 251", "", "Author Name"))
        articles.add(Article("Article 252", "", "Author Name"))
        articles.add(Article("Article 253", "", "Author Name"))
        articles.add(Article("Article 254", "", "Author Name"))
        articles.add(Article("Article 255", "", "Author Name"))
        articles.add(Article("Article 256", "", "Author Name"))
        articles.add(Article("Article 257", "", "Author Name"))
        articles.add(Article("Article 258", "", "Author Name"))
        articles.add(Article("Article 259", "", "Author Name"))
        articles.add(Article("Article 260", "", "Author Name"))
        articles.add(Article("Article 261", "", "Author Name"))
        articles.add(Article("Article 262", "", "Author Name"))
        articles.add(Article("Article 263", "", "Author Name"))

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
