package com.radlance.championshipfinal.presentation.catalog

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.championshipfinal.R
import com.radlance.championshipfinal.presentation.common.BaseColumn
import com.radlance.championshipfinal.presentation.home.ProductViewModel
import com.radlance.uikit.component.button.AppButton
import com.radlance.uikit.component.button.ButtonState
import com.radlance.uikit.component.button.CartButton
import com.radlance.uikit.component.button.SecondaryButton
import com.radlance.uikit.component.card.PrimaryCard
import com.radlance.uikit.component.search.AppSearchField
import com.radlance.uikit.theme.CustomTheme

@Composable
fun CatalogScreen(
    navigateToCart: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val fetchResultUiState by viewModel.fetchResultUiState.collectAsState()
    val changeInCartStatusUiState by viewModel.changeInCartStatusUiState.collectAsState()

    var searchFieldValue by rememberSaveable { mutableStateOf("") }

    changeInCartStatusUiState.Show(
        onSuccess = {},
        onLoading = { id ->
            LaunchedEffect(Unit) {
                id?.let {
                    viewModel.updateLocalInCartStatus(it)
                }
            }
        },
        onError = { id ->
            LaunchedEffect(Unit) {
                id?.let {
                    viewModel.updateLocalInCartStatus(it)
                }
            }
        }
    )




    fetchResultUiState.Show(
        onSuccess = { fetchContent ->
            Box {

                BaseColumn(modifier = modifier, horizontalPadding = 0.dp) {
                    Spacer(Modifier.height(CustomTheme.elevation.spacing24dp))
                    AppSearchField(
                        value = searchFieldValue,
                        onValueChange = { searchFieldValue = it },
                        hint = stringResource(R.string.search_descriptions),
                        additionalContent = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Spacer(Modifier.width(38.dp))
                                Image(
                                    painter = painterResource(R.drawable.ic_user),
                                    contentDescription = "ic_user"
                                )
                            }
                        },
                        modifier = Modifier.padding(horizontal = CustomTheme.elevation.spacing20dp)
                    )
                    if (searchFieldValue.isNotBlank()) {
                        val searchResult = fetchContent.products.filter {
                            it.title.contains(searchFieldValue, ignoreCase = true)
                        }

                        if (searchResult.isNotEmpty()) {
                            LazyColumn(
                                verticalArrangement = Arrangement.spacedBy(CustomTheme.elevation.spacing16dp),
                                contentPadding = PaddingValues(
                                    start = CustomTheme.elevation.spacing20dp,
                                    end = CustomTheme.elevation.spacing20dp,
                                    top = 25.dp,
                                    bottom = 180.dp
                                ),
                                modifier = Modifier.weight(1f)
                            ) {
                                items(
                                    items = searchResult,
                                    key = { it.id }
                                ) { product ->
                                    with(product) {
                                        PrimaryCard(
                                            title = title,
                                            category = fetchContent.categories.first {
                                                it.id == categoryId
                                            }.title,
                                            price = price,
                                            onCartClick = { viewModel.changeProductInCartStatus(id) },
                                            inCart = quantityInCart != 0,
                                            description = description,
                                            materialCost = materialCost
                                        )
                                    }
                                }
                            }
                        } else {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = stringResource(R.string.nothng_found),
                                    style = CustomTheme.typography.title3SemiBold.copy(
                                        color = CustomTheme.colors.placeholder
                                    )
                                )
                            }
                        }
                    } else {
                        Spacer(Modifier.height(CustomTheme.elevation.spacing32dp))

                        var selectedCategoryId by rememberSaveable { mutableIntStateOf(1) }

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(CustomTheme.elevation.spacing16dp),
                            contentPadding = PaddingValues(horizontal = CustomTheme.elevation.spacing20dp)
                        ) {
                            items(
                                items = fetchContent.categories,
                                key = { category -> category.id }) { category ->
                                Crossfade(selectedCategoryId) { categoryId ->
                                    if (category.id == categoryId) {
                                        AppButton(
                                            onClick = { selectedCategoryId = category.id },
                                            label = category.title,
                                            buttonState = ButtonState.Chips
                                        )
                                    } else {
                                        SecondaryButton(
                                            onClick = { selectedCategoryId = category.id },
                                            label = category.title,
                                            buttonState = ButtonState.Chips
                                        )
                                    }
                                }
                            }
                        }

                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(CustomTheme.elevation.spacing16dp),
                            contentPadding = PaddingValues(
                                start = CustomTheme.elevation.spacing20dp,
                                end = CustomTheme.elevation.spacing20dp,
                                top = 25.dp,
                                bottom = 170.dp
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            items(
                                items = fetchContent.products.filter { it.categoryId == selectedCategoryId },
                                key = { it.id }
                            ) { product ->
                                with(product) {
                                    PrimaryCard(
                                        title = title,
                                        category = fetchContent.categories.first {
                                            it.id == categoryId
                                        }.title,
                                        price = price,
                                        onCartClick = { viewModel.changeProductInCartStatus(id) },
                                        inCart = quantityInCart != 0,
                                        description = description,
                                        materialCost = materialCost
                                    )
                                }
                            }
                        }
                    }
                }
                val totalPrice = fetchContent.products.filter {
                    it.quantityInCart != 0
                }.sumOf { it.price }

                if (totalPrice > 0) {
                    Column(
                        modifier = Modifier
                            .safeGesturesPadding()
                            .align(Alignment.BottomCenter)
                    ) {
                        CartButton(
                            onClick = navigateToCart,
                            label = stringResource(R.string.to_cart),
                            totalPrice = totalPrice,
                            modifier = Modifier.padding(horizontal = CustomTheme.elevation.spacing20dp)
                        )
                        Spacer(Modifier.height(99.dp))
                    }

                }
            }
        },
        onError = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
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
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
    )
}
