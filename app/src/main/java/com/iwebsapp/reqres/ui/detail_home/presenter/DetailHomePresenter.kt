package com.iwebsapp.reqres.ui.detail_home.presenter

import com.iwebsapp.reqres.ui.detail_home.view.DetailHomeView

interface DetailHomePresenter : DetailHomeView {
    fun init(id: String)
}