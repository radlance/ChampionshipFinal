package com.radlance.championshipfinal.presentation.profile

import com.radlance.championshipfinal.domain.profile.ProfileRepository
import com.radlance.championshipfinal.domain.profile.User
import com.radlance.championshipfinal.presentation.common.BaseViewModel
import com.radlance.championshipfinal.presentation.common.FetchResultMapper
import com.radlance.championshipfinal.presentation.common.FetchResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : BaseViewModel() {
    private val _loadProfileUiState = MutableStateFlow<FetchResultUiState<User>>(FetchResultUiState.Initial())
    val loadProfileUiState: StateFlow<FetchResultUiState<User>> = _loadProfileUiState.onStart {
        loadProfile()
    }.stateInViewModel(initialValue = FetchResultUiState.Initial())

    fun loadProfile() {
        _loadProfileUiState.value = FetchResultUiState.Loading()

        handle(action = profileRepository::loadProfile) {
            _loadProfileUiState.value = it.map(FetchResultMapper())
        }
    }
}