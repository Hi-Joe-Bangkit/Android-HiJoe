package id.capstone.hijoe.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import id.capstone.hijoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        setupView()
    }

    private fun setupView() {
        binding!!.btnOpenCamera.setOnClickListener { v: View? -> Toast.makeText(this, "open camera", Toast.LENGTH_SHORT).show() }
        binding!!.btnOpenGallery.setOnClickListener { v: View? -> Toast.makeText(this, "open gallery", Toast.LENGTH_SHORT).show() }
    }
}