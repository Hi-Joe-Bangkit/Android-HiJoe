package id.capstone.hijoe.ui.result

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.capstone.hijoe.R
import id.capstone.hijoe.databinding.ActivityResultBinding
import id.capstone.hijoe.domain.model.Plant
import id.capstone.hijoe.ui.dialog.AttentionDialog
import id.capstone.hijoe.ui.main.MainActivity
import id.capstone.hijoe.util.toExactDouble
import id.capstone.hijoe.util.toPercentage
import kotlin.math.roundToInt

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    private lateinit var plant: Plant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        plant = intent.getParcelableExtra(PLANT_DATA_KEY) ?: Plant()

        setupView()
    }

    private fun setupView() {
        if (plant.accuracy < 0.5f) {
            val dialogParam = AttentionDialog.Params(
                    title = getString(R.string.attention),
                    content = getString(R.string.desc_under50percent)
            )

            AttentionDialog(dialogParam).show(supportFragmentManager, null)
        }

        with(binding) {
            val percentage = plant.accuracy.toExactDouble().toPercentage()

            tvDiseaseName.text = plant.disease
            tvPlantName.text = plant.plant
            tvDiseaseDescription.text = plant.desc
            tvDiseaseSolution.text = plant.solution
            tvDiseaseAccuracy.text = percentage
            circularBarDiseaseAccuracy.progress = (plant.accuracy * 100).roundToInt()

            btnResultToHome.setOnClickListener {
                startActivity(Intent(this@ResultActivity, MainActivity::class.java))
                finish()
            }
        }
    }

    companion object {
        const val PLANT_DATA_KEY = "The Plant"
    }
}