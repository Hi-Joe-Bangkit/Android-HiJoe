package id.capstone.hijoe.ui.process

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import id.capstone.hijoe.R
import id.capstone.hijoe.databinding.ActivityProcessBinding
import id.capstone.hijoe.domain.model.Plant
import id.capstone.hijoe.ui.main.MainActivity
import id.capstone.hijoe.ui.result.ResultActivity
import id.capstone.hijoe.ui.result.ResultActivity.Companion.PLANT_DATA_KEY
import id.capstone.hijoe.util.extension.toast

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

                    val plant = Plant(
                            id = 0,
                            plant = "Pepper Bell",
                            disease = "Bacterial Spot",
                            desc =  getString(R.string.dummy_desc),
                            solution = getString(R.string.dummy_solution),
                            accuracy = state.accuracy
                    )

                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra(PLANT_DATA_KEY, plant)
                    startActivity(intent)
                    finish()
                }
                is ProcessViewModel.ProcessState.Error -> {
                    toast("Done but error")
                    Log.e(TAG, state.message)

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                is ProcessViewModel.ProcessState.Loading -> {}
            }
        })
    }

    companion object {
        const val BITMAP_KEY = "THE BITMAP"
    }
}