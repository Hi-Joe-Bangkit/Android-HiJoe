package id.capstone.hijoe.ui.process

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.capstone.hijoe.data.ml.ImageClassification
import id.capstone.hijoe.data.vo.RequestResult
import id.capstone.hijoe.domain.usecase.IdentifyUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProcessViewModel
@Inject constructor(
        private val imageClassification: ImageClassification,
        private val identifyUseCase: IdentifyUseCase
) : ViewModel() {

    private val _state = MutableLiveData<ProcessState>()
    val state : LiveData<ProcessState>
        get() = _state

    fun classify(bitmap: Bitmap?) {
        if (bitmap == null) {
            _state.postValue(ProcessState.Error(RequestResult.NOT_DEFINED))
            return
        }

        viewModelScope.launch {
            // TODO: 29/05/2021 remove this
            delay(5000)
            try {
                imageClassification.classify(bitmap)

                // TODO: 29/05/2021 uncomment when end-point is ready
//                val identifyParams = IdentifyUseCase.IdentifyParams(
//                        id = imageClassification.position
//                )
//
//                identifyUseCase(identifyParams)
//                        .collect { value ->
//                            when(value) {
//                                is Result.Success -> {
//                                    if (value.data.plant.isEmpty()) {
//                                        _state.postValue(ProcessState.Empty)
//                                    } else {
//                                        value.data.accuracy = imageClassification.maxValue
//                                        _state.postValue(ProcessState.Success(imageClassification.position, imageClassification.maxValue))
//                                    }
//                                }
//                                is Result.Error -> {
//                                    _state.postValue(ProcessState.Error(value.cause))
//                                }
//                            }
//                        }

                // TODO: 30/05/2021 delete this one when end-point is ready
                _state.postValue(ProcessState.Success(imageClassification.position, imageClassification.maxValue))
            } catch (t: Throwable) {
                t.printStackTrace()
                _state.postValue(ProcessState.Error(RequestResult.TENSOR_FLOW_ERROR))
            }
        }
    }

    sealed class ProcessState {
        data class Success(
                val position: Int,
                val accuracy: Float
        ) : ProcessState()
        data class Error(val cause: RequestResult) : ProcessState()
        object Empty : ProcessState()
    }
}