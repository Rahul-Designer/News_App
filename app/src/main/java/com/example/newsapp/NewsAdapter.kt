package com.example.newsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.Model.Article
import kotlinx.android.synthetic.main.item_recyclerview.view.*

class NewsAdapter(private val context: Context, private val articles: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(info: Article) {
            itemView.date.text = info.publishedAt
            itemView.txt_author.text = info.author
            itemView.txt_heading.text = info.title
            itemView.txt_description.text = info.description

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_recyclerview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var article = articles[position]
        Glide.with(context).load(article.urlToImage).into(holder.itemView.newsImage)
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}