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

    private val _updateProductQuantityUiState =
        MutableStateFlow<FetchResultUiState<Int>>(FetchResultUiState.Initial())
    val updateProductQuantityUiState: StateFlow<FetchResultUiState<Int>>
        get() = _updateProductQuantityUiState.asStateFlow()

    private val _removeProductFromCartUiState =
        MutableStateFlow<FetchResultUiState<Int>>(FetchResultUiState.Initial())
    val removeProductFromCartUiState: StateFlow<FetchResultUiState<Int>>
        get() = _removeProductFromCartUiState.asStateFlow()

    private var lastRemovedProductQuantity: Int = 0

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
                productRepository.addProductToCart(productId)
            }
        ) {
            _changeInCartStatusUiState.value = it.map(FetchResultMapper())
        }
    }

    fun changeProductQuantity(productId: Int, newQuantity: Int) {
        _updateProductQuantityUiState.value = FetchResultUiState.Loading(productId)
        handle(
            action = {
                delay(100)
                productRepository.changeCartQuantity(productId, newQuantity)
            }
        ) {
            _updateProductQuantityUiState.value = it.map(FetchResultMapper())
        }
    }

    fun removeProductFromCart(productId: Int) {
        _removeProductFromCartUiState.value = FetchResultUiState.Loading(productId)
        handle(
            action = {
                delay(100)
                productRepository.removeProductFromCart(productId)
            }
        ) {
            _removeProductFromCartUiState.value = it.map(FetchResultMapper())
        }
    }

    fun removeLocalProductFromCart(productId: Int, recover: Boolean = false) {
        val state = _fetchResultUiState.value
        if (state is FetchResultUiState.Success) {
            val updatedProducts = state.data.products.map {
                if (it.id == productId) {
                    it.copy(
                        quantityInCart = if (recover) {
                            lastRemovedProductQuantity
                        } else {
                            lastRemovedProductQuantity = it.quantityInCart
                            0
                        }
                    )
                } else it
            }

            val updatedState = state.data.copy(products = updatedProducts)
            _fetchResultUiState.value = FetchResultUiState.Success(updatedState)
        }
    }

    fun updateLocalInCartStatus(productId: Int) {
        val state = _fetchResultUiState.value
        if (state is FetchResultUiState.Success) {
            val updatedProducts = state.data.products.map {
                if (it.id == productId) {
                    it.copy(quantityInCart = if (it.quantityInCart == 0) 1 else 0)
                } else it
            }

            val updatedState = state.data.copy(products = updatedProducts)
            _fetchResultUiState.value = FetchResultUiState.Success(updatedState)
        }
    }

    fun updateLocalQuantity(productId: Int, increment: Boolean) {
        val state = _fetchResultUiState.value
        if (state is FetchResultUiState.Success) {
            val updatedProducts = state.data.products.map {
                if (it.id == productId) {
                    it.copy(quantityInCart = if (increment) it.quantityInCart.inc() else it.quantityInCart.dec())
                } else it
            }

            val updatedState = state.data.copy(products = updatedProducts)
            _fetchResultUiState.value = FetchResultUiState.Success(updatedState)
        }
    }
}