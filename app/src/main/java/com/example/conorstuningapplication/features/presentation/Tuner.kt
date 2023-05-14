package com.example.conorstuningapplication.features.presentation

import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.conorstuningapplication.common.presentation.permission

@Composable
fun Tuner() {
    val context = LocalContext.current
    Column {
        permission(
            permission = android.Manifest.permission.RECORD_AUDIO,
            permissionComposable = { composabeWithAudio() },
            clickableComposable = { click -> Button(onClick = { click() }) { Text("recordAudio") } }
        )
        if (ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            composabeWithAudio()
        }
    }
}

@Composable
fun composabeWithAudio() {
    val viewModel = hiltViewModel<tunerviewModel>()
    Button(onClick = { (viewModel::run)() }) {}

    val pitch = viewModel.pitch.collectAsStateWithLifecycle(0)
    Text(pitch.value.toString())

    Text("1 (E)\t329.63 Hz\tE4\n" +
            "2 (B)\t246.94 Hz\tB3\n" +
            "3 (G)\t196.00 Hz\tG3\n" +
            "4 (D)\t146.83 Hz\tD3\n" +
            "5 (A)\t110.00 Hz\tA2\n" +
            "6 (E)\t82.41 Hz\tE2")
}