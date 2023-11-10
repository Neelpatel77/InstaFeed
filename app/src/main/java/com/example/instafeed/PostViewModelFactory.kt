package com.example.instafeed

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PostViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    private fun createJsonPlaceholderApiService(): JsonPlaceholderApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/") // Base URL of your API
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(JsonPlaceholderApiService::class.java)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            val apiService = createJsonPlaceholderApiService()
            return PostViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
