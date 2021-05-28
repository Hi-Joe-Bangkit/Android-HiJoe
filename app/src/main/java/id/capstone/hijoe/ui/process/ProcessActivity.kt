package id.capstone.hijoe.ui.process

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.capstone.hijoe.databinding.ActivityProcessBinding

class ProcessActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProcessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProcessBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}