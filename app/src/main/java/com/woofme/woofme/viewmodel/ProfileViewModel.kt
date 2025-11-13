package com.woofme.woofme.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.woofme.woofme.R
import com.woofme.woofme.common.profiles
import com.woofme.woofme.model.Profile
import com.woofme.woofme.model.ProfileData
import com.woofme.woofme.model.ProfileListContainer
import com.woofme.woofme.navigation.ScreenRoutes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ProfileUiState(
    val profile: Profile = ProfileData.getPreloadedProfile(),

    val imageToDeleteIndex: Int? = null,

    val dogs: List<Int> = List(15) { R.drawable.perro2 },
    val showDialog: Boolean = false,
    val selectedItem: Int = 0,
    val isUserOwnProfile: Boolean = true
)

class ProfileViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    //con esto pasamos el id del usuario del home a profile
    private val targetUserId: String? = savedStateHandle.get<String>(ScreenRoutes.USER_ID)



    // Estado interno mutable (solo el ViewModel puede cambiarlo)
    private val _uiState = MutableStateFlow(ProfileUiState())

    // Estado público inmutable que observa la UI
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init{

        //TODO Esto deberiamos cambiarlo para obtener los datos de la APi
        val gson = Gson()
        val container = gson.fromJson(profiles, ProfileListContainer::class.java)
        val targetProfile: Profile? = container.profiles.find { it.id == targetUserId } ?: ProfileData.getPreloadedProfile()
        val isUserOwnProfile = if (targetUserId == "{userId}") true else false
        Log.d("ProfileViewModel", "targetUserId: $targetUserId")


// Luego, actualizas el estado de forma segura:
        _uiState.value = ProfileUiState(profile = targetProfile!!,
            isUserOwnProfile = isUserOwnProfile
            )


    }

    fun toggleDialog(show: Boolean, index: Int? = null) {
        _uiState.update { it.copy(showDialog = show,
            imageToDeleteIndex = index

        ) }
    }

    fun selectItem(index: Int) {
        _uiState.update { it.copy(selectedItem = index) }
    }

    fun confirmDialogAction() {
        _uiState.update { currentState ->
            val index = currentState.imageToDeleteIndex

            if (index != null && index >= 0 && index < currentState.profile.images.size) {
                val updatedImages = currentState.profile.images.toMutableList().apply {
                    removeAt(index)
                }.toList()

                currentState.copy(
                    profile = currentState.profile.copy(images = updatedImages),
                    showDialog = false,
                    imageToDeleteIndex = null
                )
            } else {
                currentState.copy(showDialog = false, imageToDeleteIndex = null)
            }
        }
    }

    fun addImage(imageUri: String) {
        _uiState.update { currentState ->
            // 1. Obtener la lista actual de URIs
            val currentImages = currentState.profile.images.toMutableList()

            // 2. Insertar la nueva URI (al principio o al final, según prefieras)
            val updatedImages = listOf(imageUri) + currentImages

            // 3. Devolver el nuevo estado con el perfil actualizado
            currentState.copy(
                profile = currentState.profile.copy(images = updatedImages)
            )
        }
    }

    fun changeProfileImage(imageUri: String) {
        _uiState.update { currentState ->
            val currentProfileImage = currentState.profile.profileImageRes

            // 2. Insertar la nueva URI (al principio o al final, según prefieras)
            val updatedProfileImage = imageUri

            currentState.copy(
                profile = currentState.profile.copy(profileImageRes = updatedProfileImage)
            )
        }
    }
}
