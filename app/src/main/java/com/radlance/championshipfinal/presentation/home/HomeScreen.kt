package com.radlance.championshipfinal.presentation.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.championshipfinal.R
import com.radlance.championshipfinal.domain.home.Sale
import com.radlance.championshipfinal.presentation.common.BaseColumn
import com.radlance.uikit.component.button.AppButton
import com.radlance.uikit.component.button.ButtonState
import com.radlance.uikit.component.button.SecondaryButton
import com.radlance.uikit.component.card.PrimaryCard
import com.radlance.uikit.component.search.AppSearchField
import com.radlance.uikit.theme.CustomTheme

@Composable
fun HomeScreen(
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

    BaseColumn(modifier = modifier, horizontalPadding = 0.dp) {
        fetchResultUiState.Show(
            onSuccess = { fetchContent ->
                Spacer(Modifier.height(CustomTheme.elevation.spacing24dp))
                AppSearchField(
                    value = searchFieldValue,
                    onValueChange = { searchFieldValue = it },
                    hint = stringResource(R.string.search_descriptions),
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
                                bottom = 92.dp
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

                    Text(
                        text = stringResource(R.string.sales_and_news),
                        style = CustomTheme.typography.title3SemiBold.copy(
                            color = CustomTheme.colors.placeholder
                        ),
                        modifier = Modifier.padding(horizontal = CustomTheme.elevation.spacing20dp)
                    )
                    Spacer(Modifier.height(CustomTheme.elevation.spacing16dp))

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(CustomTheme.elevation.spacing16dp),
                        contentPadding = PaddingValues(horizontal = CustomTheme.elevation.spacing20dp)
                    ) {
                        val sales = listOf(
                            Sale(
                                title = "Рубашка воскресенье",
                                price = 8000,
                                imageResId = R.drawable.sale_example_first,
                                backgroundBrush = Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF76B3FF),
                                        Color(0xFFCDE3FF)
                                    )
                                ),
                                offset = { IntOffset(x = 90, y = 0) }
                            ),
                            Sale(
                                title = "Шорты вторник",
                                price = 4000,
                                imageResId = R.drawable.sale_example_second,
                                backgroundBrush = Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF97D9F0),
                                        Color(0xFF92E9D4)
                                    )
                                ),
                                offset = { IntOffset(1, 1) },
                                scale = 1.2f
                            )
                        )

                        items(items = sales, key = { it.title }) { sale ->
                            SaleCard(sale)
                        }
                    }

                    Spacer(Modifier.height(CustomTheme.elevation.spacing32dp))

                    Text(
                        text = stringResource(R.string.сatalog_of_descriptions),
                        style = CustomTheme.typography.title3SemiBold.copy(
                            color = CustomTheme.colors.placeholder
                        ),
                        modifier = Modifier.padding(horizontal = CustomTheme.elevation.spacing20dp)
                    )
                    Spacer(Modifier.height(CustomTheme.elevation.spacing16dp))


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
                            bottom = 92.dp
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
            },
            onError = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.loading_error),
                        style = CustomTheme.typography.title3SemiBold.copy(
                            color = CustomTheme.colors.placeholder
                        ),
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