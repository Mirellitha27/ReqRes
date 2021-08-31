package com.iwebsapp.reqres.ui.account.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.iwebsapp.reqres.MainActivity
import com.iwebsapp.reqres.R
import com.iwebsapp.reqres.databinding.ActivityCreateAccountBinding
import com.iwebsapp.reqres.ui.account.presenter.CreateAccountPresenter
import com.iwebsapp.reqres.ui.account.presenter.CreateAccountPresenterImpl
import com.iwebsapp.reqres.ui.login.presenter.LoginPresenterImpl
import com.iwebsapp.reqres.ui.login.view.LoginActivity

class CreateAccountActivity : AppCompatActivity(), CreateAccountView {
    private lateinit var binding: ActivityCreateAccountBinding
    private var presenter: CreateAccountPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_account)
        setUpVariable()

        binding.txtGetInto.setOnClickListener {
            presenter!!.register(binding.editEmail.text.toString(), binding.editPassword.text.toString())
        }
    }

    private fun setUpVariable() {
        if (presenter == null) {
            presenter = CreateAccountPresenterImpl(this)
        }
    }

    override fun toast(text: Int) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    override fun goMain() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}