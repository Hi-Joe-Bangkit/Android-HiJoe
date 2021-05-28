package id.capstone.hijoe.util

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.ColorInt
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import id.capstone.hijoe.R

object PermissionUtil {
    val isSdkHigherThanAndroidQ: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    fun Context.safeCheckPermission(permissions: List<String>, onGranted: () -> Unit) {
        Dexter.withContext(this)
                .withPermissions(permissions)
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                        p0?.let {
                            if (it.areAllPermissionsGranted()) {
                                onGranted()
                                return@let
                            }
                            if (it.isAnyPermissionPermanentlyDenied) {
                                showDialogOpenSetting().show()
                            }
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                            p0: MutableList<PermissionRequest>?,
                            p1: PermissionToken?
                    ) {
                        p1?.continuePermissionRequest()
                    }
                })
                .withErrorListener { toast("Please wait") }
                .onSameThread()
                .check()
    }

    private fun Context.showDialogOpenSetting() : AlertDialog {
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