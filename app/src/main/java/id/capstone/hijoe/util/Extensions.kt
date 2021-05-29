package id.capstone.hijoe.util

import android.content.Context
import android.widget.Toast
import java.text.NumberFormat

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Float.toExactDouble() : Double {
    return this.toString().toDouble()
}

fun Double.toPercentage() : String {
    val numberFormat = NumberFormat.getPercentInstance()
    numberFormat.minimumFractionDigits = 0
    return numberFormat.format(this)
}