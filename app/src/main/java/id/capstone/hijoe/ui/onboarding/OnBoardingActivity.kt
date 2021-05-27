package id.capstone.hijoe.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import id.capstone.hijoe.R
import id.capstone.hijoe.databinding.ActivityOnBoardingBinding
import id.capstone.hijoe.ui.main.MainActivity
import id.capstone.hijoe.ui.onboarding.listener.OnClickBoardingListener
import id.capstone.hijoe.ui.onboarding.placeholder.FirstBoardingFragment
import id.capstone.hijoe.ui.onboarding.placeholder.SecondBoardingFragment

class OnBoardingActivity : AppCompatActivity(), OnClickBoardingListener {

    private lateinit var binding: ActivityOnBoardingBinding

    private val firstBoarding: FirstBoardingFragment by lazy { FirstBoardingFragment() }
    private val secondBoarding: SecondBoardingFragment by lazy { SecondBoardingFragment() }
    private val boardingList = listOf(firstBoarding, secondBoarding)
    private val boardingAdapter = OnBoardingAdapter(boardingList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
    }

    private fun setupViewPager() {
        binding.onBoardingViewPager.adapter = boardingAdapter
    }

    override fun onButtonClicked(view: View) {
        val position = binding.onBoardingViewPager.currentItem

        when(view.id) {
            R.id.btnNextBoarding -> {
                binding.onBoardingViewPager.currentItem = position+1
            }
            R.id.btnDoneBoarding -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else -> {
                Log.v(this.javaClass.simpleName, "Illegal id from placeholder")
            }
        }
    }
}