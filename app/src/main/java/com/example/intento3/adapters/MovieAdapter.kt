package com.example.intento3.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.intento3.R
import com.example.intento3.entities.Movie
import kotlinx.android.synthetic.main.movie_cardview.view.*

class MovieAdapter(var movies:List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_cardview,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) = with(itemView) {
            this.movie_name.text = movie.Title
        }
    }

    fun updateList(newMovies:List<Movie>){
        newMovies.forEach{
            println("sera que funciona? shi")
        }
        this.movies=newMovies
        notifyDataSetChanged()
    }


}