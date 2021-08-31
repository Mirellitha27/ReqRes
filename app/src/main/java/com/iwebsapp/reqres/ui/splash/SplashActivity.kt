package com.iwebsapp.reqres.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.iwebsapp.reqres.MainActivity
import com.iwebsapp.reqres.R
import com.iwebsapp.reqres.databinding.ActivitySplashBinding
import com.iwebsapp.reqres.ui.login.view.LoginActivity
import com.iwebsapp.reqres.utils.UserSession

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val SPLASH_TIME_OUT:Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        Handler().postDelayed({
            validate()
            finish()
        }, SPLASH_TIME_OUT)
    }

    private fun validate() {
        val userSession = UserSession(this)
        if (userSession.getToken().isNullOrEmpty()) {
            goLogin()
        } else {
            goMain()
        }
    }

    private fun goMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun goLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}