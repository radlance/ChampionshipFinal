package com.radlance.championshipfinal.presentation.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.championshipfinal.R
import com.radlance.championshipfinal.presentation.common.BaseColumn
import com.radlance.championshipfinal.presentation.home.ProductViewModel
import com.radlance.uikit.component.button.AppButton
import com.radlance.uikit.component.button.ButtonState
import com.radlance.uikit.component.card.CartCard
import com.radlance.uikit.component.header.BigHeader
import com.radlance.uikit.theme.CustomTheme

@Composable
fun CartScreen(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val fetchResultUiState by viewModel.fetchResultUiState.collectAsState()
    val updateQuantityUiState by viewModel.updateProductQuantityUiState.collectAsState()
    var incrementCurrent by rememberSaveable { mutableStateOf(false) }

    updateQuantityUiState.Show(
        onSuccess = {},

        onError = { id ->
            LaunchedEffect(Unit) {
                id?.let { viewModel.updateLocalQuantity(it, !incrementCurrent) }
            }
        },

        onLoading = { id ->
            LaunchedEffect(Unit) {
                id?.let { viewModel.updateLocalQuantity(it, incrementCurrent) }
            }
        }
    )

    BaseColumn(modifier = modifier) {
        Spacer(Modifier.height(CustomTheme.elevation.spacing16dp))
        BigHeader(
            title = stringResource(R.string.cart),
            onBackPressed = onBackPressed,
            onRemoveClick = {}
        )
        Spacer(Modifier.height(CustomTheme.elevation.spacing32dp))
        fetchResultUiState.Show(
            onSuccess = { fetchContent ->
                val cartItems = fetchContent.products.filter { it.quantityInCart != 0 }
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(CustomTheme.elevation.spacing32dp)
                ) {
                    items(items = cartItems, key = { it.id }) { cartItem ->
                        with(cartItem) {
                            CartCard(
                                title = title,
                                price = price,
                                quantity = quantityInCart,
                                onChangeQuantity = { quantity, increment ->
                                    viewModel.changeProductQuantity(cartItem.id, quantity)
                                    incrementCurrent = increment
                                }
                            )
                        }
                    }

                    item {
                        Row(modifier = Modifier.padding(vertical = CustomTheme.elevation.spacing32dp)) {
                            Text(
                                text = stringResource(R.string.sum),
                                style = CustomTheme.typography.title2SemiBold
                            )
                            Spacer(Modifier.weight(1f))
                            Text(
                                text = "${cartItems.sumOf { it.price * it.quantityInCart }} ₽",
                                style = CustomTheme.typography.title2SemiBold
                            )
                        }
                    }
                }

                AppButton(
                    onClick = {},
                    label = stringResource(R.string.move_to_checkout),
                    buttonState = ButtonState.Big
                )
                Spacer(Modifier.height(32.dp))
            },
            onError = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.loading_error),
                        modifier = Modifier.padding(vertical = CustomTheme.elevation.spacing8dp)
                    )
                    AppButton(
                        onClick = viewModel::fetchContent,
                        label = stringResource(R.string.retry),
                        buttonState = ButtonState.Chips
                    )
                }
            },
            onLoading = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    CircularProgressIndicator()
                }
            }
        )
    }
}