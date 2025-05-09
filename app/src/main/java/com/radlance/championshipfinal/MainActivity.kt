package com.radlance.championshipfinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.radlance.uikit.component.button.AppButton
import com.radlance.uikit.component.button.BubbleButton
import com.radlance.uikit.component.button.BubbleButtonState
import com.radlance.uikit.component.button.ButtonState
import com.radlance.uikit.component.button.CartButton
import com.radlance.uikit.component.button.LoginButton
import com.radlance.uikit.component.button.OutlinedAppButton
import com.radlance.uikit.component.button.SecondaryButton
import com.radlance.uikit.component.card.PrimaryCard
import com.radlance.uikit.component.input.EnterInputField
import com.radlance.uikit.component.input.SingleInputField
import com.radlance.uikit.component.search.AppSearchField
import com.radlance.uikit.component.select.AppSelector
import com.radlance.uikit.component.tabbar.BottomTab
import com.radlance.uikit.component.tabbar.BottomTabBar
import com.radlance.uikit.theme.CustomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomTheme {
                var fieldValue by rememberSaveable { mutableStateOf("") }
                var singleValue by rememberSaveable { mutableStateOf("") }
                var errorMessage by rememberSaveable { mutableStateOf("") }

                var selectedItemFirst by rememberSaveable { mutableStateOf<String?>(null) }
                var selectedItemSecond by rememberSaveable { mutableStateOf<String?>(null) }
                var selectedItemThird by rememberSaveable { mutableStateOf<String?>(null) }

                var searchFieldValue by rememberSaveable { mutableStateOf("") }
                var secondSearchFieldValue by rememberSaveable { mutableStateOf("") }

                Scaffold { padding ->
                    Scaffold(
                        bottomBar = {
                            BottomTabBar(
                                selectedTab = BottomTab.Home,
                                onTabClick = {},
                                bottomPadding = padding.calculateBottomPadding()
                            )
                        }
                    ) { contentPadding ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(CustomTheme.colors.white)
                                .verticalScroll(rememberScrollState())
                                .padding(contentPadding)
                                .safeDrawingPadding(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(24.dp),
                                modifier = Modifier.padding(
                                    start = 26.dp,
                                    end = 26.dp,
                                    bottom = contentPadding.calculateBottomPadding()
                                )
                            ) {
                                EnterInputField(
                                    value = fieldValue,
                                    onValueChange = {
                                        fieldValue = it
                                        errorMessage = ""
                                    },
                                    errorMessage = errorMessage,
                                    enabled = true,
                                    label = "Имя",
                                    hint = "Введите имя"
                                )

                                SingleInputField(
                                    value = singleValue,
                                    onValueChange = {
                                        if (it.length < 2 && it.isDigitsOnly()) {
                                            singleValue = it
                                        }
                                    },
                                    hint = "1"
                                )

                                AppSelector(
                                    options = listOf("Мужской", "Женский"),
                                    selectedItem = selectedItemFirst,
                                    hint = "Пол",
                                    label = "",
                                    onItemSelect = { selectedItemFirst = it },
                                )

                                AppSelector(
                                    options = listOf("Мужской", "Женский"),
                                    selectedItem = selectedItemSecond,
                                    hint = "Пол",
                                    onItemSelect = { selectedItemSecond = it },
                                    label = "",
                                    closable = true
                                )

                                AppSelector(
                                    options = listOf("Сегодня, 16 апреля", "Завтра, 17 апреля"),
                                    selectedItem = selectedItemThird,
                                    hint = "Дата",
                                    onItemSelect = { selectedItemThird = it },
                                    label = "Дата",
                                    closable = false
                                )

                                AppSearchField(
                                    value = searchFieldValue,
                                    onValueChange = { searchFieldValue = it },
                                    hint = "Искать описание"
                                )

                                AppSearchField(
                                    value = secondSearchFieldValue,
                                    onValueChange = { secondSearchFieldValue = it },
                                    hint = "Искать описание",
                                    cancellable = true
                                )

                                AppButton(
                                    onClick = { errorMessage = "Введите ваше имя" },
                                    label = "Показать ошибку",
                                    buttonState = ButtonState.Big
                                )

                                AppButton(
                                    onClick = { errorMessage = "Введите ваше имя" },
                                    label = "Показать ошибку (выключена)",
                                    enabled = false,
                                    buttonState = ButtonState.Big
                                )

                                OutlinedAppButton(
                                    onClick = { errorMessage = "" },
                                    label = "Скрыть ошибку",
                                    buttonState = ButtonState.Big
                                )

                                SecondaryButton(
                                    onClick = { errorMessage = "" },
                                    label = "Добавить проект",
                                    buttonState = ButtonState.Medium
                                )

                                AppButton(
                                    onClick = { errorMessage = "" },
                                    label = "Добавить",
                                    enabled = false,
                                    buttonState = ButtonState.Small
                                )

                                AppButton(
                                    onClick = { errorMessage = "" },
                                    label = "Популярное",
                                    buttonState = ButtonState.Chips
                                )

                                SecondaryButton(
                                    onClick = { errorMessage = "" },
                                    label = "Популярное",
                                    enabled = false,
                                    buttonState = ButtonState.Chips
                                )

                                CartButton(
                                    onClick = { errorMessage = "" },
                                    totalPrice = 500,
                                    label = "В корзину"
                                )

                                LoginButton(
                                    onClick = { errorMessage = "" },
                                    icon = R.drawable.ic_vk_login,
                                    label = "Войти с VK"
                                )

                                LoginButton(
                                    onClick = { errorMessage = "" },
                                    icon = R.drawable.ic_yandex_login,
                                    label = "Войти с Yandex"
                                )

                                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                                    BubbleButton(
                                        icon = R.drawable.ic_back,
                                        state = BubbleButtonState.Small
                                    )
                                    BubbleButton(icon = R.drawable.ic_filter)
                                    BubbleButton(icon = R.drawable.ic_message)
                                }
                                PrimaryCard(
                                    title = "Рубашка Воскресенье для машинного вязания",
                                    category = "Мужская одежда",
                                    price = 300,
                                    inCart = false
                                )
                                PrimaryCard(
                                    title = "Рубашка Воскресенье для машинного вязания",
                                    category = "Мужская одежда",
                                    price = 300,
                                    inCart = true
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}