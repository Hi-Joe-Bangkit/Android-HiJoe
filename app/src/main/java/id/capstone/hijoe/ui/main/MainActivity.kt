package id.capstone.hijoe.ui.main

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import id.capstone.hijoe.R
import id.capstone.hijoe.databinding.ActivityMainBinding
import id.capstone.hijoe.ui.dialog.AttentionDialog
import id.capstone.hijoe.ui.process.ProcessActivity
import id.capstone.hijoe.ui.process.ProcessActivity.Companion.BITMAP_KEY
import id.capstone.hijoe.util.BitmapUtil.createBitmapFromUri
import id.capstone.hijoe.util.BitmapUtil.toByteArray
import id.capstone.hijoe.util.PermissionUtil.isSdkHigherThanAndroidQ
import id.capstone.hijoe.util.PermissionUtil.safeCheckPermission
import id.capstone.hijoe.util.extension.toast

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        with(binding) {
            btnOpenCamera.setOnClickListener {
                safeCheckPermission(listOf(Manifest.permission.CAMERA)) {
                    openCamera()
                }
            }
            btnOpenGallery.setOnClickListener {
                if (isSdkHigherThanAndroidQ) {
                    openGallery()
                } else {
                    safeCheckPermission(listOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        openGallery()
                    }
                }
            }
        }
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
        } catch (e: ActivityNotFoundException) {
            toast(getString(R.string.camera_not_found))
        }
    }

    private fun openGallery() {
        val getIntent = Intent(Intent.ACTION_GET_CONTENT)
        getIntent.type = "image/*"

        val chooserIntent = Intent.createChooser(getIntent, getString(R.string.select_image))

        try {
            startActivityForResult(chooserIntent, GALLERY_REQUEST_CODE)
        } catch (e: ActivityNotFoundException) {
            toast(getString(R.string.gallery_not_found))
        }
    }

    // TODO: 30/05/2021 use ActivityResultContract
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when(requestCode) {
                CAMERA_REQUEST_CODE -> {
                    bitmap = data?.extras?.get("data") as Bitmap

                    val intent = Intent(this, ProcessActivity::class.java)
                    val bundle = Bundle()
                    bundle.putByteArray(BITMAP_KEY, bitmap.toByteArray())
                    intent.putExtras(bundle)

                    try {
                        startActivity(intent)
                    } catch (t: Throwable) {
                        toast(getString(R.string.error_big_image))
                    }
                }
                GALLERY_REQUEST_CODE -> {
                    bitmap = createBitmapFromUri(data?.data)

                    val intent = Intent(this, ProcessActivity::class.java)
                    val bundle = Bundle()
                    bundle.putByteArray(BITMAP_KEY, bitmap.toByteArray())
                    intent.putExtras(bundle)

                    try {
                        startActivity(intent)
                    } catch (t: Throwable) {
                        toast(getString(R.string.error_big_image))
                    }
                }
                else -> {
                    Log.e(this.javaClass.simpleName, "Illegal request code")
                }
            }
        }
    }

    companion object {
        private const val CAMERA_REQUEST_CODE = 1000
        private const val GALLERY_REQUEST_CODE = 1001
    }
}