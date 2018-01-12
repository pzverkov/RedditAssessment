package com.zve.redditassessment.api

import com.zve.redditassessment.models.RedditListing
import com.zve.redditassessment.models.RedditResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Peter on 11.01.2018.
 */

interface RedditService {

    @GET("/top/.json")
    fun getTopListing(@Query("count") count: Int, @Query("after") after: String): Call<RedditResponse<RedditListing>>

    @GET("/r/{subreddit}/comments/{id}.json")
    fun getComments(@Path("subreddit") subreddit: String, @Path("id") id: String): Call<List<RedditResponse<RedditListing>>>

}
