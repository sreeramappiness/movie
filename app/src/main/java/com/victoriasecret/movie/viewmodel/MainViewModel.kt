package com.victoriasecret.movie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victoriasecret.movie.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val mainRepository = MainRepository()

    val usersSuccessLiveData = mainRepository.usersSuccessLiveData
    val usersFailureLiveData = mainRepository.usersFailureLiveData

    val popularSuccessLiveData = mainRepository.popularListSuccessLiveData
    val popularFailureLiveData = mainRepository.popularListFailureLiveData

    val detailsListSuccessLiveData = mainRepository.detailsListSuccessLiveData
    val detailsListFailureLiveData = mainRepository.detailsListFailureLiveData

    fun getUsers() {
        //this is coroutine viewmodel scope to call suspend fun of repo
        viewModelScope.launch { mainRepository.getUsers() }

    }

    fun getPopular() {
        viewModelScope.launch { mainRepository.getPopular() }
    }

    fun getDetails(movieId: String) {
        viewModelScope.launch { mainRepository.getDetails(movieId) }
    }
}