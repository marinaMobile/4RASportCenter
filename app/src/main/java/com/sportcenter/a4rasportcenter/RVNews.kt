package com.sportcenter.a4rasportcenter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RVNews  (private val context: Context, private val articles: List<ItemArticle>): RecyclerView.Adapter<RVNews.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var image: ImageView = itemView.findViewById(R.id.imageView)
        var newTitle: TextView = itemView.findViewById(R.id.title_tv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_lt, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val article: ItemArticle = articles[position]
        holder.newTitle.text = article.title
        Glide.with(context)
            .load(article.urlToImage)
            .placeholder(R.drawable.football)
            .error(R.drawable.football)
            .fallback(R.drawable.football)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}