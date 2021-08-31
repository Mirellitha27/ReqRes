package com.iwebsapp.reqres.ui.account.presenter

import com.iwebsapp.reqres.ui.account.view.CreateAccountView

interface CreateAccountPresenter : CreateAccountView{
    fun register(email: String, password: String)
}