package com.example.digiturkminiapp.ui.filmlist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.digiturkminiapp.R
import com.example.digiturkminiapp.interfaces.RecyclerItemClickListener
import com.example.digiturkminiapp.model.Film
import com.squareup.picasso.Picasso

class MovieRecyclerAdapter(private val filmList: List<Film>)
    : RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder>(){

    private var itemClickListener: RecyclerItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.row_movie, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return filmList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = filmList[position]
        holder.movieName.text = movie.title
        Picasso.get()
            .load("http://image.tmdb.org/t/p/w185${movie.poster_path}")
            .into(holder.movieImage)

        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(holder.itemView,position)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var movieName = itemView.findViewById<TextView>(R.id.movieNameTextView)!!
        var movieImage = itemView.findViewById<ImageView>(R.id.movieImageView)!!
    }

    fun setItemClickListener(clickListener: RecyclerItemClickListener){
        itemClickListener = clickListener
    }
}