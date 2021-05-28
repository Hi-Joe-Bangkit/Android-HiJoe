package id.capstone.hijoe.util

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
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
    }

    private fun Context.showDialogOpenSetting() : AlertDialog {
        val pkg = packageName

        return AlertDialog.Builder(this).apply {
            setTitle(R.string.access_denied)
            setMessage(R.string.desc_access_denied)
            setPositiveButton(R.string.go_to_setting) { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.data = Uri.fromParts("package", pkg, null)
                startActivity(intent)
            }
            setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
        }.create()
    }
}