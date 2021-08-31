package com.iwebsapp.reqres.ui.login.presenter

import com.iwebsapp.reqres.ui.login.interactor.LoginInteractor
import com.iwebsapp.reqres.ui.login.interactor.LoginInteractorImpl
import com.iwebsapp.reqres.ui.login.view.LoginView

class LoginPresenterImpl(private var view: LoginView) : LoginPresenter {
    private var interactor: LoginInteractor? = null

    init {
        this.interactor = LoginInteractorImpl(this)
    }

    override fun login(email: String, password: String) {
        interactor!!.login(email, password)
    }

    override fun toast(text: Int) {
        view.toast(text)
    }

    override fun goMain(token: String) {
        view.goMain(token)
    }
}