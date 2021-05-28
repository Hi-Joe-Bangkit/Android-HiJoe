package id.capstone.hijoe.ui.process

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.capstone.hijoe.ml.ImageClassification
import kotlinx.coroutines.launch

class ProcessViewModel : ViewModel() {

    private val _state = MutableLiveData<ProcessState>()
    val state : LiveData<ProcessState>
        get() = _state

    fun classify(context: Context, bitmap: Bitmap) {
        val imageClassification = ImageClassification(context)

        viewModelScope.launch {
            _state.postValue(ProcessState.Loading)
            try {
                imageClassification.classify(bitmap)
                _state.postValue(ProcessState.Success(imageClassification.position, imageClassification.maxValue))
            } catch (t: Throwable) {
                t.printStackTrace()
                _state.postValue(ProcessState.Error(t.message.toString()))
            }
        }
    }

    sealed class ProcessState {
        data class Success(
                val position: Int,
                val accuracy: Float
        ) : ProcessState()
        data class Error(val message: String) : ProcessState()
        object Loading : ProcessState()
    }
}