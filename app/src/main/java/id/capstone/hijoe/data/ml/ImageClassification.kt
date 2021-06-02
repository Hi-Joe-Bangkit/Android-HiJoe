package id.capstone.hijoe.data.ml

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import id.capstone.hijoe.ml.AutoModel2PlantModel
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.common.TensorProcessor
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import javax.inject.Inject

class ImageClassification
@Inject constructor(private val context: Context) {
    private var _position = -1
    private var _maxValue = -1.0f

    val position: Int
        get() = _position

    val maxValue: Float
        get() = _maxValue

    fun classify(bitmap: Bitmap) {
        val probabilityProcessor =
                TensorProcessor.Builder()
                        .add(NormalizeOp(0f, 255f))
                        .build()

        val imageProcessor =
                ImageProcessor.Builder()
                        .add(ResizeOp(227, 227, ResizeOp.ResizeMethod.BILINEAR))
                        .build()

        var tensorImage = TensorImage(DataType.FLOAT32)
        tensorImage.load(bitmap)
        tensorImage = imageProcessor.process(tensorImage)

        val model = AutoModel2PlantModel.newInstance(context)

        Log.v(this.javaClass.simpleName, tensorImage.tensorBuffer.buffer.toString())

        val outputs = model.process(probabilityProcessor.process(tensorImage.tensorBuffer))
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer

        Log.v(this.javaClass.simpleName, "${outputFeature0.floatArray.size}")

        for (index in outputFeature0.floatArray.indices) {
            Log.v(this.javaClass.simpleName, "$index -> ${outputFeature0.floatArray[index]}")
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