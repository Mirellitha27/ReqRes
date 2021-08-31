package com.iwebsapp.reqres.api

import com.iwebsapp.reqres.model.detail_home.DetailHomeModel
import com.iwebsapp.reqres.model.home.HomeModel
import com.iwebsapp.reqres.model.login.ResponseLogin
import com.iwebsapp.reqres.model.register.ResponseRegister
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    //login
    @FormUrlEncoded
    @POST("api/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseLogin>

    //register
    @FormUrlEncoded
    @POST("api/register")
    fun register(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseRegister>

    @GET("api/users")
    fun getUsers(): Call<HomeModel>

    @GET("api/users/{idUser}")
    fun getCurrentUser(
        @Path("idUser") idUser: String,
    ): Call<DetailHomeModel>
}