package com.example.madlevel6example.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel6example.api.TriviaApi
import com.example.madlevel6example.api.TriviaApiService
import com.example.madlevel6example.model.Trivia
import kotlinx.coroutines.withTimeout

class TriviaRepository {
    private val triviaApiService: TriviaApiService = TriviaApi.createApi()

    private val _trivia: MutableLiveData<Trivia> = MutableLiveData()

    val trivia: LiveData<Trivia>
        get() = _trivia

    suspend fun getRandomNumberTrivia() {
        try {
            val result = withTimeout(5000) {
                triviaApiService.getRandomNumberTrivia()
            }

            _trivia.value = result
        } catch (error: Throwable) {
            throw TriviaRefreshError("Unable to refresh trivia", error)
        }
    }

    class TriviaRefreshError(message: String, cause: Throwable) : Throwable(message, cause)
}