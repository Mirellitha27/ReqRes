package com.iwebsapp.reqres.api

import com.iwebsapp.reqres.model.detail_home.DetailHomeModel
import com.iwebsapp.reqres.model.home.HomeModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/users")
    fun getUsers(): Call<HomeModel>

    @GET("api/users/{idUser}")
    fun getCurrentUser(
        @Path("idUser") idUser: String,
    ): Call<DetailHomeModel>
}