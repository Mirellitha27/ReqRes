package com.iwebsapp.reqres.ui.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.iwebsapp.reqres.MainActivity
import com.iwebsapp.reqres.R
import com.iwebsapp.reqres.databinding.ActivityLoginBinding
import com.iwebsapp.reqres.ui.account.view.CreateAccountActivity
import com.iwebsapp.reqres.ui.login.presenter.LoginPresenter
import com.iwebsapp.reqres.ui.login.presenter.LoginPresenterImpl
import com.iwebsapp.reqres.utils.UserSession

class LoginActivity : AppCompatActivity(), LoginView {
    private lateinit var binding: ActivityLoginBinding
    private var presenter: LoginPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setUpVariable()
        binding.textViewAccount.setOnClickListener { goCreateAccount() }
        binding.txtGetInto.setOnClickListener {
            presenter!!.login(binding.editEmail.text.toString(), binding.editPassword.text.toString())
        }
    }

    private fun setUpVariable() {
        if (presenter == null) {
            presenter = LoginPresenterImpl(this)
        }
    }

    override fun toast(text: Int) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private fun goCreateAccount() {
        val intent = Intent(this, CreateAccountActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun goMain(token: String) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        val userSession = UserSession(this)
        userSession.setToken(token)
        finish()
    }
}