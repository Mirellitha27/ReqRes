package com.iwebsapp.reqres.ui.home.view

import android.widget.ImageView
import com.iwebsapp.reqres.model.home.Datum

interface HomeView {
    fun setDataAdapter(data: ArrayList<Datum>)
    fun showImage(urlImg: String?, imageView: ImageView?)
    fun showDetail(idUser: String)
}