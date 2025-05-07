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

class ServicesUnionStatesFragment : Fragment(), ArticlesAdapter.OnArticleClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ArticlesAdapter
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val articles = mutableListOf<Article>()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_services_union_states, container, false)

        // Initialize RecyclerView and Adapter
        recyclerView = view.findViewById(R.id.recyclerViewServicesUnionStates)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ArticlesAdapter(articles, this)
        recyclerView.adapter = adapter

        // Load article titles
        initializeArticles()

        return view
    }

    private fun initializeArticles() {
        // Preload articles for Services under the Union and States (Articles 308 to 323)
        articles.add(Article("Article 308", "", "Author Name"))
        articles.add(Article("Article 309", "", "Author Name"))
        articles.add(Article("Article 310", "", "Author Name"))
        articles.add(Article("Article 311", "", "Author Name"))
        articles.add(Article("Article 312", "", "Author Name"))
        articles.add(Article("Article 313", "", "Author Name"))
        articles.add(Article("Article 314", "", "Author Name"))
        articles.add(Article("Article 315", "", "Author Name"))
        articles.add(Article("Article 316", "", "Author Name"))
        articles.add(Article("Article 317", "", "Author Name"))
        articles.add(Article("Article 318", "", "Author Name"))
        articles.add(Article("Article 319", "", "Author Name"))
        articles.add(Article("Article 320", "", "Author Name"))
        articles.add(Article("Article 321", "", "Author Name"))
        articles.add(Article("Article 322", "", "Author Name"))
        articles.add(Article("Article 323", "", "Author Name"))

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
