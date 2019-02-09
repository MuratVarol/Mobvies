package com.example.mobvies.remote.enums

import com.example.mobvies.remote.interfaces.IMovieType

enum class MovieType
constructor(val type: String) : IMovieType{

    POPULAR("popular") {
        override fun getMovieType(): String { return "popular" }
    },

    UPCOMING("upcoming") {
        override fun getMovieType(): String { return "upcoming" }
    },

    LATEST("latest") {
        override fun getMovieType(): String { return "latest" }
    }

}