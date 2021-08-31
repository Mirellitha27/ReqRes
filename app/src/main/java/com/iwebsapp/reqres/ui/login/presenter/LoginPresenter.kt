package com.iwebsapp.reqres.ui.login.presenter

import com.iwebsapp.reqres.ui.login.view.LoginView

interface LoginPresenter : LoginView {
    fun login(email: String, password: String)
}