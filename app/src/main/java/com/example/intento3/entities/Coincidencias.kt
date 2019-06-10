package com.example.intento3.entities

import androidx.room.PrimaryKey
import com.squareup.moshi.Json

data class Coincidencias(
        @field:Json(name="totalResults")
        var totalResults : String,
        @field:Json(name="Search")
        var Search : List<Movie>,
        @field:Json(name="Response")
        var Response : String
)