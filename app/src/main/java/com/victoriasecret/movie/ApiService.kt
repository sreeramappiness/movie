package com.victoriasecret.movie

import com.victoriasecret.movie.model.details.DetailsList
import com.victoriasecret.movie.model.movieList.PopularList
import com.victoriasecret.movie.model.user.User
import com.victoriasecret.movie.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST(Constants.GET_USER)
    suspend fun getUsers(): Response<User>

    @GET(Constants.GET_POPULAR)
    suspend fun getPopular(): Response<PopularList>

    @GET("3/tv/{movieId}?api_key=684d3704abc8da89227249d9c9c878c7&language=en-US")
    suspend fun getDetails(@Path(value = "movieId", encoded = false) movieId: String?): Response<DetailsList>


}