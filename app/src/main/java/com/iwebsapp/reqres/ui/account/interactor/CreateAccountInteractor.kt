package com.iwebsapp.reqres.ui.account.interactor

interface CreateAccountInteractor {
    fun register(email: String, password: String)
}