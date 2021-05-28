package id.capstone.hijoe.ui.main

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import id.capstone.hijoe.R
import id.capstone.hijoe.databinding.ActivityMainBinding
import id.capstone.hijoe.util.PermissionUtil.isSdkHigherThanAndroidQ
import id.capstone.hijoe.util.PermissionUtil.safeCheckPermission
import id.capstone.hijoe.util.toast

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

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
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
    }

    private fun openGallery() {
        val getIntent = Intent(Intent.ACTION_GET_CONTENT)
        getIntent.type = "image/*"

        val chooserIntent = Intent.createChooser(getIntent, getString(R.string.select_image))

        startActivityForResult(chooserIntent, GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when(resultCode) {
                CAMERA_REQUEST_CODE -> {
                    // todo: get camera bitmap
                }
                GALLERY_REQUEST_CODE -> {
                    // todo: get gallery data
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