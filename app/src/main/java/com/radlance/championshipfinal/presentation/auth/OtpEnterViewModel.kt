package com.radlance.championshipfinal.presentation.auth

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OtpEnterViewModel : ViewModel() {

    private val otpList = mutableStateListOf("", "", "", "")

    private val _navigateEvent = MutableSharedFlow<Unit>(replay = 0)
    val navigateEvent: SharedFlow<Unit> = _navigateEvent.asSharedFlow()

    fun enterValue(value: String, index: Int) {
        otpList[index] = value

        if (otpList.all { it.isNotBlank() }) {
            viewModelScope.launch {
                _navigateEvent.emit(Unit)
            }
        }
    }

    fun otpList(): List<String> = otpList

}