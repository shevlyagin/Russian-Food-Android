package com.shevlyagin.food_pmm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.shevlyagin.food_pmm.ui.MyScaffold
import com.shevlyagin.food_pmm.ui.theme.Food_PMMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Food_PMMTheme {
                MyScaffold()
            }
        }
    }
}