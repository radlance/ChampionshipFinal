package com.radlance.championshipfinal.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
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
}