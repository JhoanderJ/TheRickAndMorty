package com.jhoander.therickandmorty.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.jhoander.therickandmorty.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        goToMain()
    }

    private fun goToMain() {
        Handler(Looper.getMainLooper()).postDelayed({
            val mIntent = Intent(this@SplashActivity, CharacterListActivity::class.java)
            startActivity(mIntent)
            finish()
        }, 3000)
    }
}
