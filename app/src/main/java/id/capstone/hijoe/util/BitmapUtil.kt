package id.capstone.hijoe.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri


object BitmapUtil {
    fun Context.createBitmapFromUri(uri: Uri?) : Bitmap {
        val inputStream = contentResolver.openInputStream(uri ?: Uri.EMPTY)
        return BitmapFactory.decodeStream(inputStream)
    }
}