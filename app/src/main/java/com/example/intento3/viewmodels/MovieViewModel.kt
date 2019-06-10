package com.example.intento3.viewmodels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.intento3.RoomDB
import com.example.intento3.entities.Movie
import com.example.intento3.repositories.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(private val app : Application) : AndroidViewModel(app) {
    private val repository : MovieRepository

    init {
        val movieDao = RoomDB.getInstance(app).movieDao()
        repository = MovieRepository(movieDao)
    }

    fun insert(movie: Movie) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(movie)
    }

    fun getAll(): LiveData<List<Movie>> {
        return repository.getAll()
    }

    private suspend fun nuke()=repository.nuke()

    fun retriveMovie(clue : String) = viewModelScope.launch {
        this@MovieViewModel.nuke()
        val response =  repository.retrieveRepoAsync(clue).await()

        if(response.isSuccessful) with(response.body()?.Search){
            this?.forEach {
                this@MovieViewModel.insert(it)
                Log.d("terminado",it.toString()+" ingresada correctamente")
            }
        }else with(response){
            Log.d("error",response.toString())
            when(this.code()){
                404->{
                    Toast.makeText(app, "pelicula no encontrada", Toast.LENGTH_LONG).show()

                }
            }
        }
    }
}