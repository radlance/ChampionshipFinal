package com.radlance.championshipfinal.presentation.home

import com.radlance.championshipfinal.domain.home.FetchContent
import com.radlance.championshipfinal.domain.home.ProductRepository
import com.radlance.championshipfinal.presentation.common.BaseViewModel
import com.radlance.championshipfinal.presentation.common.FetchResultMapper
import com.radlance.championshipfinal.presentation.common.FetchResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : BaseViewModel() {
    private val _fetchResultUiState =
        MutableStateFlow<FetchResultUiState<FetchContent>>(FetchResultUiState.Initial())
    val fetchResultUiState: StateFlow<FetchResultUiState<FetchContent>> =
        _fetchResultUiState.onStart {
            fetchContent()
        }.stateInViewModel(initialValue = FetchResultUiState.Initial())

    private val _changeInCartStatusUiState =
        MutableStateFlow<FetchResultUiState<Int>>(FetchResultUiState.Initial())
    val changeInCartStatusUiState: StateFlow<FetchResultUiState<Int>>
        get() = _changeInCartStatusUiState.asStateFlow()

    fun fetchContent() {
        _fetchResultUiState.value = FetchResultUiState.Loading()
        handle(action = productRepository::fetchHomeContent) {
            _fetchResultUiState.value = it.map(FetchResultMapper())
        }
    }

    fun changeProductInCartStatus(productId: Int) {
        _changeInCartStatusUiState.value = FetchResultUiState.Loading(productId)
        handle(
            action = {
                delay(100) // TODO remove with real network queries
                productRepository.changeInCartStatus(productId)
            }
        ) {
            _changeInCartStatusUiState.value = it.map(FetchResultMapper())
        }
    }

    fun updateLocalInCartStatus(productId: Int) {
        val state = _fetchResultUiState.value
        if (state is FetchResultUiState.Success) {
            val updatedProducts = state.data.products.map {
                if (it.id == productId) {
                    it.copy(inCart = !it.inCart)
                } else it
            }

            val updatedState = state.data.copy(products = updatedProducts)
            _fetchResultUiState.value = FetchResultUiState.Success(updatedState)
        }
    }
}