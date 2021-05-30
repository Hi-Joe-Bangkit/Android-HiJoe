package id.capstone.hijoe.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.ByteArrayOutputStream


object BitmapUtil {
    fun Context.createBitmapFromUri(uri: Uri?): Bitmap {
        val inputStream = contentResolver.openInputStream(uri ?: Uri.EMPTY)
        return BitmapFactory.decodeStream(inputStream)
    }

    fun Bitmap.toByteArray(): ByteArray {
        val stream = ByteArrayOutputStream()
        this.compress(
                Bitmap.CompressFormat.JPEG,
                100,
                stream
        )
        return stream.toByteArray()
    }

    fun ByteArray.toBitmap(): Bitmap {
        val options = BitmapFactory.Options()
        options.inMutable = true
        return BitmapFactory.decodeByteArray(this, 0, size, options)
    }
}