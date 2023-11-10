package com.example.instafeed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewModel(private val jsonPlaceholderApiService: JsonPlaceholderApiService) : ViewModel() {

    private val _postsData = MutableLiveData<List<Post>?>()
    val postsData: LiveData<List<Post>?>
        get() = _postsData

    fun fetchPosts() {
        jsonPlaceholderApiService.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    _postsData.postValue(response.body())
                } else {
                    _postsData.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                _postsData.postValue(null)
            }
        })
    }
}

