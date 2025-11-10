package com.woofme.woofme.viewmodel

import androidx.lifecycle.ViewModel
import com.woofme.woofme.model.Profile
import com.woofme.woofme.model.ProfileData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HomeScreenUiState(
    val profileDetails: List<Profile> = emptyList()
)

class HomeScreenViewModel: ViewModel(){
    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()

    init{
        loadProfileDispay()
    }

    fun loadProfileDispay(){
        var initialProfile = ProfileData.getPreloadedProfile()
        var list = emptyList<Profile>()
        for(i in 0..3){
            val profile = initialProfile.copy(id = initialProfile.id + i, name = initialProfile.name + i)
            list = list + profile

        }
        _uiState.value = _uiState.value.copy(profileDetails = list)

    }

}