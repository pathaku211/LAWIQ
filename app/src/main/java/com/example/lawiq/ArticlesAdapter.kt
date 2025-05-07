package com.example.lawiq

import Article // Ensure this is the correct import for your Article class
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ArticlesAdapter(
    private val articles: List<Article>,
    private val listener: OnArticleClickListener
) : RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

    interface OnArticleClickListener {
        fun onArticleClick(article: Article)
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textView15)
        val forwardImageView: ImageView = itemView.findViewById(R.id.imageView3)
        val cardView: CardView = itemView.findViewById(R.id.cardView) // Ensure this ID exists in your XML

        init {
            cardView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onArticleClick(articles[position]) // Notify the listener
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false) // Ensure item_card.xml exists
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.titleTextView.text = article.title // Ensure the title exists in the Article class
        // Set other fields as necessary
    }

    override fun getItemCount() = articles.size // Returns the number of articles
}
