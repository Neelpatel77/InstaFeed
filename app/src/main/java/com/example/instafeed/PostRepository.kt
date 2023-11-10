package com.example.instafeed


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostRepository(private val apiService: JsonPlaceholderApiService) {

    fun getPosts(onPostsFetched: (List<Post>?) -> Unit) {
        apiService.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val posts = response.body()
                    onPostsFetched(posts)
                } else {
                    onPostsFetched(null)
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                onPostsFetched(null)
            }
        })
    }
}
