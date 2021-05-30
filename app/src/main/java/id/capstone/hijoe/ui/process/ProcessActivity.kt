package id.capstone.hijoe.ui.process

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import id.capstone.hijoe.R
import id.capstone.hijoe.data.vo.RequestResult
import id.capstone.hijoe.databinding.ActivityProcessBinding
import id.capstone.hijoe.domain.model.Plant
import id.capstone.hijoe.ui.dialog.AttentionDialog
import id.capstone.hijoe.ui.main.MainActivity
import id.capstone.hijoe.ui.result.ResultActivity
import id.capstone.hijoe.ui.result.ResultActivity.Companion.PLANT_DATA_KEY
import id.capstone.hijoe.util.BitmapUtil.toBitmap
import id.capstone.hijoe.util.extension.toast

@AndroidEntryPoint
class ProcessActivity : AppCompatActivity() {
    private val TAG = this.javaClass.simpleName

    private lateinit var binding: ActivityProcessBinding

    private var bitmap: Bitmap? = null

    private val processViewModel: ProcessViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProcessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras ?: Bundle()
        bitmap = bundle.getByteArray(BITMAP_KEY)?.toBitmap()

        processViewModel.classify(bitmap)
        observeData()
    }

    private fun observeData() {
        processViewModel.state.observe(this, { state ->
            when(state) {
                is ProcessViewModel.ProcessState.Success -> {
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
                    Log.e(TAG, state.cause.name)

                    showErrorDialog(state.cause)
                }
                is ProcessViewModel.ProcessState.Empty -> {
                    showErrorDialog(RequestResult.EMPTY)
                }
            }
        })
    }

    private fun showErrorDialog(cause: RequestResult) {
        val attentionParams = when(cause) {
            RequestResult.TENSOR_FLOW_ERROR -> {
                AttentionDialog.Params(
                        title = getString(R.string.attention),
                        content = getString(R.string.desc_tflite_error)
                )
            }
            RequestResult.NO_CONNECTION -> {
                AttentionDialog.Params(
                        title = getString(R.string.attention),
                        content = getString(R.string.desc_network_error)
                )
            }
            RequestResult.EMPTY -> {
                AttentionDialog.Params(
                        title = getString(R.string.attention),
                        content = getString(R.string.desc_empty_data)
                )
            }
            else -> {
                AttentionDialog.Params(
                        title = getString(R.string.attention),
                        content = getString(R.string.desc_unknown_error)
                )
            }
        }

        AttentionDialog(attentionParams) {
            onBackPressed()
        }.show(supportFragmentManager, null)
    }

    companion object {
        const val BITMAP_KEY = "THE BITMAP"
    }
}