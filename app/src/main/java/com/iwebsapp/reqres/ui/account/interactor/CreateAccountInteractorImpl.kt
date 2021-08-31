package com.iwebsapp.reqres.ui.account.interactor

import android.util.Log
import com.iwebsapp.reqres.R
import com.iwebsapp.reqres.api.ApiService
import com.iwebsapp.reqres.api.RetrofitClient
import com.iwebsapp.reqres.model.register.ResponseRegister
import com.iwebsapp.reqres.ui.account.presenter.CreateAccountPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateAccountInteractorImpl(private var presenter: CreateAccountPresenter) : CreateAccountInteractor {

    override fun register(email: String, password: String){
        val apiService = RetrofitClient.buildService(ApiService::class.java)
        val call = apiService.register(email, password)
        call.enqueue(object : Callback<ResponseRegister> {
            override fun onResponse(call: Call<ResponseRegister>, response: Response<ResponseRegister>) {
                if (response.isSuccessful)  {
                    Log.d("TAG", "response ${response.body()!!.token}")
                    presenter.toast(R.string.successful_login)
                    presenter.goMain()
                } else  {
                    presenter.toast(R.string.occurred_error)
                }
            }
            override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                Log.d("TAG", "onFailure: $t")
            }
        })
    }
}