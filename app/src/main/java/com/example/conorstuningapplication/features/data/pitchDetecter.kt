package com.example.conorstuningapplication.features.data

import android.util.Log
import androidx.lifecycle.viewModelScope
import be.tarsos.dsp.AudioDispatcher
import be.tarsos.dsp.io.android.AudioDispatcherFactory
import be.tarsos.dsp.pitch.PitchDetectionHandler
import be.tarsos.dsp.pitch.PitchProcessor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

class pitchDetecter @Inject constructor() {

    private val sampleRate = 44100
    private val bufferSize = 4096
    private val dispatcher: AudioDispatcher = AudioDispatcherFactory.fromDefaultMicrophone(sampleRate,bufferSize,bufferSize/2)

    private val pdh = PitchDetectionHandler() {res, _ ->
        val pitchInHz = res.pitch
        processPitch(pitchInHz)
    }



    private val pitchflow = MutableStateFlow<Float>(0f)
    val pitchflowview: Flow<Float> = pitchflow

    private val pitchProcessor = PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.FFT_YIN, sampleRate.toFloat(), bufferSize, pdh)

    fun record() {
            dispatcher.addAudioProcessor(pitchProcessor)
            dispatcher.run()
        }

    private fun processPitch(pitchInHz: Float) {
        val scope = CoroutineScope(
            SupervisorJob() + Dispatchers.IO
        )
        scope.launch {
            Log.d("pitch", pitchInHz.toString())
            pitchflow.emit(pitchInHz)
        }
    }

}
