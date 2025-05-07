package com.example.lawiq

import Article
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StateGovtFragment : Fragment(), ArticlesAdapter.OnArticleClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ArticlesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_state_govt, container, false)

        // Create a list of articles from 152 to 237
        val articles = mutableListOf<Article>()
        for (i in 152..237) {
            articles.add(Article("Article $i", "Description of Article $i", "Author Name"))
        }

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ArticlesAdapter(articles, this)
        recyclerView.adapter = adapter

        return view
    }

    override fun onArticleClick(article: Article) {
        val detailFragment = ArticleDetailFragment()
        val bundle = Bundle()
        bundle.putParcelable("article", article) // Pass the article data
        detailFragment.arguments = bundle

        // Navigate to the detail fragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailFragment) // Ensure this ID matches your container ID
            .addToBackStack(null)
            .commit()
    }
}
