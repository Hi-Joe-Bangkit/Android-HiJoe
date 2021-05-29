package id.capstone.hijoe.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import dagger.hilt.android.AndroidEntryPoint
import id.capstone.hijoe.R
import id.capstone.hijoe.data.local.Session
import id.capstone.hijoe.databinding.ActivitySplashScreenBinding
import id.capstone.hijoe.ui.main.MainActivity
import id.capstone.hijoe.ui.onboarding.OnBoardingActivity
import javax.inject.Inject

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    @Inject lateinit var session: Session

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = if (session.isFirstTime) {
                Intent(this, OnBoardingActivity::class.java)
            } else {
                Intent(this, MainActivity::class.java)
            }

            startActivity(intent)
            finish()
        }, 1500)
    }
}