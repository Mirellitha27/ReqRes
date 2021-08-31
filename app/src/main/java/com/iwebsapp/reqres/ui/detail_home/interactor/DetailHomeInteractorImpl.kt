package com.iwebsapp.reqres.ui.detail_home.interactor

import android.util.Log
import com.iwebsapp.reqres.api.ApiService
import com.iwebsapp.reqres.api.RetrofitClient
import com.iwebsapp.reqres.model.detail_home.DetailHomeModel
import com.iwebsapp.reqres.ui.detail_home.presenter.DetailHomePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailHomeInteractorImpl(private var presenter: DetailHomePresenter): DetailHomeInteractor {

    override fun init(id: String) {
        val apiService = RetrofitClient.buildService(ApiService::class.java)
        val call = apiService.getCurrentUser(id)
        call.enqueue(object : Callback<DetailHomeModel> {
            override fun onResponse(call: Call<DetailHomeModel>, response: Response<DetailHomeModel>) {
                if (response.isSuccessful) {
                    val id = response.body()!!.data!!.id.toString()
                    val email = response.body()!!.data!!.email
                    val name = response.body()!!.data!!.first_name
                    val lastName = response.body()!!.data!!.last_name
                    val image = response.body()!!.data!!.avatar
                    presenter.setData(id, email, name, lastName, image)
                }
            }
            override fun onFailure(call: Call<DetailHomeModel>, t: Throwable) {
                Log.d("TAG", "onFailure: $t")
            }

        })
    }
}