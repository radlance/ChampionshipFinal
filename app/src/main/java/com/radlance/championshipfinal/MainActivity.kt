package com.radlance.championshipfinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.radlance.uikit.component.input.EnterInputField
import com.radlance.uikit.component.input.SingleInputField
import com.radlance.uikit.component.select.AppSelector
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

                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(24.dp),
                        modifier = Modifier.padding(horizontal = 26.dp)
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

                        Button(onClick = { errorMessage = "Введите ваше имя" }) {
                            Text("Показать ошибку")
                        }

                        Button(onClick = { errorMessage = "" }) {
                            Text("Скрыть ошибку")
                        }
                    }
                }
            }
        }
    }
}