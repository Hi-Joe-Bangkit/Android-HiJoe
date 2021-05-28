package id.capstone.hijoe.util

import android.content.Context
import android.os.Build
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import id.capstone.hijoe.util.AlertDialogUtil.showDialogOpenSetting

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
}