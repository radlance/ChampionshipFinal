package com.radlance.championshipfinal.presentation.auth

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class EnterPasswordViewModel : ViewModel() {
    private val passwordNumbers = mutableStateListOf<Int>()

    fun enterNumber(value: Int) {
        if (passwordNumbers.size < 4) {
            passwordNumbers.add(value)
        }
    }

    fun removeNumber() {
        passwordNumbers.removeLastOrNull()
    }

    fun passwordProgressList(): List<Int> = passwordNumbers

    fun resetTypedPassword() = passwordNumbers.clear()

}