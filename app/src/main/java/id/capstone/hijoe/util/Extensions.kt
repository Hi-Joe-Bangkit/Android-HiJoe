package id.capstone.hijoe.util

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import java.text.NumberFormat
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun <T> Fragment.viewLifecycle(): ReadWriteProperty<Fragment, T> =
        object : ReadWriteProperty<Fragment, T>, DefaultLifecycleObserver {

            private var binding: T? = null

            init {
                // Observe the view lifecycle of the Fragment.
                // The view lifecycle owner is null before onCreateView and after onDestroyView.
                // The observer is automatically removed after the onDestroy event.
                this@viewLifecycle
                        .viewLifecycleOwnerLiveData
                        .observe(this@viewLifecycle, { owner: LifecycleOwner? ->
                            owner?.lifecycle?.addObserver(this)
                        })
            }

            override fun onDestroy(owner: LifecycleOwner) {
                binding = null
            }

            override fun getValue(
                    thisRef: Fragment,
                    property: KProperty<*>
            ): T {
                return this.binding ?: error("Called before onCreateView or after onDestroyView.")
            }

            override fun setValue(
                    thisRef: Fragment,
                    property: KProperty<*>,
                    value: T
            ) {
                this.binding = value
            }
        }

fun Float.toExactDouble() : Double {
    return this.toString().toDouble()
}

fun Double.toPercentage() : String {
    val numberFormat = NumberFormat.getPercentInstance()
    numberFormat.minimumFractionDigits = 0
    return numberFormat.format(this)
}