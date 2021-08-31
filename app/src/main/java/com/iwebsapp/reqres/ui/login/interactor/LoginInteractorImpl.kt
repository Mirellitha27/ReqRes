package com.iwebsapp.reqres.ui.login.interactor

import android.util.Log
import com.iwebsapp.reqres.R
import com.iwebsapp.reqres.api.ApiService
import com.iwebsapp.reqres.api.RetrofitClient
import com.iwebsapp.reqres.model.login.ResponseLogin
import com.iwebsapp.reqres.ui.login.presenter.LoginPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginInteractorImpl(private var presenter: LoginPresenter) : LoginInteractor {

    override fun login(email: String, password: String) {
        val apiService = RetrofitClient.buildService(ApiService::class.java)
        val call = apiService.login(email, password)
        call.enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                if (response.isSuccessful)  {
                    Log.d("TAG", "response ${response.body()!!.token}")
                    presenter.toast(R.string.successful_login)
                    presenter.goMain()
                } else  {
                    presenter.toast(R.string.occurred_error)
                }
            }
            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                Log.d("TAG", "onFailure: $t")
                presenter.toast(R.string.occurred_error)
            }
        })
    }
}