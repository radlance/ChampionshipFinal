package com.radlance.championshipfinal.presentation.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.radlance.championshipfinal.presentation.navigation.NavGraph
import com.radlance.uikit.theme.CustomTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomTheme {
                Scaffold { contentPadding ->
                    NavGraph(
                        navController = rememberNavController(),
                        contentPadding = contentPadding
                    )
                }
            }
        }
    }
}