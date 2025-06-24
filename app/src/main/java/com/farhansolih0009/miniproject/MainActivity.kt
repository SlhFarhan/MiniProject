package com.farhansolih0009.miniproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.farhansolih0009.miniproject.navigation.SetupNavGraph
import com.farhansolih0009.miniproject.ui.theme.MiniProjectTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniProjectTheme {
                SetupNavGraph()
            }
        }
    }
}