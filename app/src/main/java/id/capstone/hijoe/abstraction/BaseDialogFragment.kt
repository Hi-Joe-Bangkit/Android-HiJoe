package id.capstone.hijoe.abstraction

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment : DialogFragment() {
    abstract fun initViewCreated()

    override fun onStart() {
        super.onStart()

        dialog?.window?.apply {
            setLayout(getBetterSize(), ViewGroup.LayoutParams.WRAP_CONTENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewCreated()
    }

    private fun getBetterSize(): Int {
        val displayMetrics = DisplayMetrics()
        requireActivity().display?.getRealMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        val whiteSpaceSize = width / 8
        return width - whiteSpaceSize
    }
}