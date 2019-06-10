package com.example.intento3.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.intento3.entities.Coincidencias
import com.example.intento3.entities.Movie
import com.example.intento3.retrofit.CoincidenciaService
import kotlinx.coroutines.Deferred
import retrofit2.Response

class MovieRepository (private val movieRepoDao : com.example.intento3.dao.MoviesDao){
    fun retrieveRepoAsync(clue:String) : Deferred<Response<Coincidencias>> {
        val paso = "d6c84f8c"
        return CoincidenciaService.getCoincidenciaService().getMovies(clue,paso)
    }

    @WorkerThread
    suspend fun insert(movie: Movie){
        movieRepoDao.insert(movie)
    }

    fun getAll(): LiveData<List<Movie>> {
        return movieRepoDao.getAllMovies()
    }

    @WorkerThread
    suspend fun nuke(){
        return movieRepoDao.nukeTable()
    }
}