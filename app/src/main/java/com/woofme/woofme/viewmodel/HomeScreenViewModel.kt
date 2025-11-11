package com.woofme.woofme.viewmodel

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.woofme.woofme.common.profiles
import com.woofme.woofme.model.Profile
import com.woofme.woofme.model.ProfileData
import com.woofme.woofme.model.ProfileListContainer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HomeScreenUiState(
    val profileDetails: List<Profile> = emptyList()
)

class HomeScreenViewModel: ViewModel(){
    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()

    val gson = Gson()

    init{
        loadProfileDispay()
    }



    fun loadProfileDispay(){
        var initialProfile = ProfileData.getPreloadedProfile()

        val container = gson.fromJson(profiles, ProfileListContainer::class.java)

        _uiState.value = _uiState.value.copy(profileDetails = container.profiles)
    }

}