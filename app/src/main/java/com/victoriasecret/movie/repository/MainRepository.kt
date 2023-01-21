package com.victoriasecret.movie.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.victoriasecret.movie.manager.RetrofitManager
import com.victoriasecret.movie.model.details.DetailsList
import com.victoriasecret.movie.model.movieList.PopularList
import com.victoriasecret.movie.model.user.User
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class MainRepository {

    private val apiService = RetrofitManager.apiService
    private val apiServiceLogin = RetrofitManager.apiServiceLogin

    val usersSuccessLiveData = MutableLiveData<User>()
    val usersFailureLiveData = MutableLiveData<Boolean>()

    val detailsListSuccessLiveData = MutableLiveData<DetailsList>()
    val detailsListFailureLiveData = MutableLiveData<Boolean>()

    val popularListSuccessLiveData = MutableLiveData<PopularList>()
    val popularListFailureLiveData = MutableLiveData<Boolean>()

    /*
    this fun is suspend fun means it will execute in different thread
     */
    suspend fun getUsers() {

        try {

            val response = apiServiceLogin.getUsers()

            if (response.isSuccessful) {
                usersSuccessLiveData.postValue(response.body())

            } else {
                usersFailureLiveData.postValue(true)
            }

        } catch (e: UnknownHostException) {
            usersFailureLiveData.postValue(true)
        } catch (e: SocketTimeoutException) {
            usersFailureLiveData.postValue(true)
        } catch (e: Exception) {
            usersFailureLiveData.postValue(true)
        }

    }

    suspend fun getPopular() {

        try {

            val response = apiService.getPopular()

            if (response.isSuccessful) {
                popularListSuccessLiveData.postValue(response.body())

            } else {
                popularListFailureLiveData.postValue(true)
            }

        } catch (e: UnknownHostException) {
            popularListFailureLiveData.postValue(true)
        } catch (e: SocketTimeoutException) {
            popularListFailureLiveData.postValue(true)
        } catch (e: Exception) {
            popularListFailureLiveData.postValue(true)
        }

    }

    suspend fun getDetails(movieId: String) {
        try {

            val response = apiService.getDetails(movieId)

            if (response.isSuccessful) {
                detailsListSuccessLiveData.postValue(response.body())

            } else {
                detailsListFailureLiveData.postValue(true)
            }

        } catch (e: UnknownHostException) {
            detailsListFailureLiveData.postValue(true)
        } catch (e: SocketTimeoutException) {
            detailsListFailureLiveData.postValue(true)
        } catch (e: Exception) {
            detailsListFailureLiveData.postValue(true)
        }
    }

    companion object {
        val TAG = MainRepository::class.java.simpleName
    }
}