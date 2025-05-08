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
import com.radlance.uikit.component.EnterInputField
import com.radlance.uikit.component.SingleInputField
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
                            label = "asd",
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