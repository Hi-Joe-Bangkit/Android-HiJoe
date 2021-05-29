package id.capstone.hijoe.util.extension

import java.text.NumberFormat

fun Float.toExactDouble() : Double {
    return this.toString().toDouble()
}

fun Double.toPercentage() : String {
    val numberFormat = NumberFormat.getPercentInstance()
    numberFormat.minimumFractionDigits = 0
    return numberFormat.format(this)
}