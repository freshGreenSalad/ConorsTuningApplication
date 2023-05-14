package com.example.conorstuningapplication.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.conorstuningapplication.features.presentation.Tuner
import com.example.conorstuningapplication.features.presentation.tunerviewModel
import com.example.conorstuningapplication.ui.theme.ConorsTuningApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ConorsTuningApplicationTheme {
                Tuner()
            }
        }
    }
}

