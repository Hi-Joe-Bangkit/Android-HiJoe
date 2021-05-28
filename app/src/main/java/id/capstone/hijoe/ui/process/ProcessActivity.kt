package id.capstone.hijoe.ui.process

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import id.capstone.hijoe.databinding.ActivityProcessBinding
import id.capstone.hijoe.util.toast

class ProcessActivity : AppCompatActivity() {
    private val TAG = this.javaClass.simpleName

    private lateinit var binding: ActivityProcessBinding

    private lateinit var bitmap: Bitmap

    private val processViewModel: ProcessViewModel by lazy {
        ViewModelProvider(this).get(ProcessViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProcessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bitmap = intent.getParcelableExtra(BITMAP_KEY)!!

        processViewModel.classify(this, bitmap)
        observeData()
    }

    private fun observeData() {
        processViewModel.state.observe(this, { state ->
            when(state) {
                is ProcessViewModel.ProcessState.Success -> {
                    toast("Done and clean")
                    Log.v(TAG, "accuracy: ${state.accuracy} in position ${state.position}")
                }
                is ProcessViewModel.ProcessState.Error -> {
                    toast("Done but error")
                    Log.e(TAG, state.message)
                }
                is ProcessViewModel.ProcessState.Loading -> {}
            }
        })
    }

    companion object {
        const val BITMAP_KEY = "THE BITMAP"
    }
}