package id.capstone.hijoe.ml

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

class ImageClassification(private val context: Context) {
    private var _position = -1
    private var _maxValue = -1.0f

    val position: Int
        get() = _position

    val maxValue: Float
        get() = _maxValue

    fun classify(bitmap: Bitmap) {
        val model = PlantDisease.newInstance(context)
        val resizedBitmap = Bitmap.createScaledBitmap(bitmap, 224, 224, true)

        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
        val tensorBuffer = TensorImage(DataType.FLOAT32)
        tensorBuffer.load(resizedBitmap)
        val byteBuffer = tensorBuffer.buffer
        inputFeature0.loadBuffer(byteBuffer)

        Log.v(this.javaClass.simpleName, byteBuffer.toString())

        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer

        for (accuracy in outputFeature0.floatArray) {
            Log.v(this.javaClass.simpleName, accuracy.toString())
        }

        _position = getHighestAccuracyPosition(outputFeature0.floatArray)
        _maxValue = outputFeature0.floatArray[_position]

        Log.v(this.javaClass.simpleName, "highest pos in $_position with acc $_maxValue")

        model.close()
    }

    private fun getHighestAccuracyPosition(data: FloatArray): Int {
        var index = 0
        var minValue = 0.0F
        for (i in data.indices) {
            if (data[i] > minValue) {
                index = i
                minValue = data[i]
            }
        }

        return index
    }
}