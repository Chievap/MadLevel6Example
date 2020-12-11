package com.example.madlevel6example.api

import com.example.madlevel6example.model.Trivia
import retrofit2.http.GET

interface TriviaApiService {

    @GET("/random/trivia?json")
    suspend fun getRandomNumberTrivia(): Trivia
}