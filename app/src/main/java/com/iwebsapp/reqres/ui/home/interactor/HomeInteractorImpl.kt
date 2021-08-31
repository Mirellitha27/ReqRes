package com.iwebsapp.reqres.ui.home.interactor

import android.util.Log
import com.iwebsapp.reqres.api.ApiService
import com.iwebsapp.reqres.api.RetrofitClient
import com.iwebsapp.reqres.model.home.Datum
import com.iwebsapp.reqres.model.home.HomeModel
import com.iwebsapp.reqres.ui.home.presenter.HomePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeInteractorImpl(private var presenter: HomePresenter?) : HomeInteractor {
    var datum = ArrayList<Datum>()

    companion object {
        private val TAG = HomeInteractorImpl::class.simpleName
    }

    override fun init() {
        val apiService = RetrofitClient.buildService(ApiService::class.java)
        val call = apiService.getUsers()
        call.enqueue(object : Callback<HomeModel> {
            override fun onResponse(call: Call<HomeModel>, response: Response<HomeModel>) {
                if (response.isSuccessful){
                    datum = response.body()!!.data as ArrayList<Datum>
                    Log.d(TAG, "isSuccessful: " + response.body())
                    presenter!!.setDataAdapter(datum)
                }
            }
            override fun onFailure(call: Call<HomeModel>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }
        })
    }

}