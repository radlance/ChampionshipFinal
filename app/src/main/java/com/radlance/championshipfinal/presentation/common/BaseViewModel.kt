package com.radlance.championshipfinal.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel : ViewModel() {

    fun <T : Any> handle(
        action: suspend () -> T,
        ui: (T) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = action.invoke()

            withContext(Dispatchers.Main) {
                ui.invoke(result)
            }
        }
    }

    fun <T> Flow<T>.stateInViewModel(initialValue: T): StateFlow<T> {
        return stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = initialValue
        )
    }
}