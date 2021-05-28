package id.capstone.hijoe.util

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import id.capstone.hijoe.R

object AlertDialogUtil {
    fun Context.showDialogOpenSetting() : AlertDialog {
        val pkg = packageName

        return AlertDialog.Builder(this).apply {
            setTitle(R.string.access_denied)
            setMessage(R.string.desc_access_denied)
            setCancelable(false)
            setPositiveButton(R.string.go_to_setting) { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.data = Uri.fromParts("package", pkg, null)
                startActivity(intent)
            }
            setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
        }.create().apply {
            val positiveButtonColor = resources.getColor(R.color.black, null)
            val negativeButtonColor = resources.getColor(R.color.gray, null)

            setOnShowListener {
                this.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(positiveButtonColor)
                this.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(negativeButtonColor)
            }
        }
    }
}