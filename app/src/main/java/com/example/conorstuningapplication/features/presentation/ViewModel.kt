package com.example.conorstuningapplication.features.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import be.tarsos.dsp.AudioDispatcher
import be.tarsos.dsp.io.android.AudioDispatcherFactory
import be.tarsos.dsp.pitch.PitchDetectionHandler
import be.tarsos.dsp.pitch.PitchDetector
import be.tarsos.dsp.pitch.PitchProcessor
import com.example.conorstuningapplication.features.data.pitchDetecter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class tunerviewModel @Inject constructor(
    private val detecter: pitchDetecter
): ViewModel() {

    val num = MutableStateFlow(0)
    fun updateNum (){
        num.value = num.value+1
    }

    val pitch = detecter.pitchflowview

    fun run (){
        viewModelScope.launch (Dispatchers.IO) {
            detecter.record()
        }
    }
}
