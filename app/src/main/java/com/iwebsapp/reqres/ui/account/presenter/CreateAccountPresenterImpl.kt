package com.iwebsapp.reqres.ui.account.presenter

import com.iwebsapp.reqres.ui.account.interactor.CreateAccountInteractor
import com.iwebsapp.reqres.ui.account.interactor.CreateAccountInteractorImpl
import com.iwebsapp.reqres.ui.account.view.CreateAccountView

class CreateAccountPresenterImpl(private var view: CreateAccountView) : CreateAccountPresenter {
    private var interactor: CreateAccountInteractor? = null
    init {
        this.interactor = CreateAccountInteractorImpl(this)
    }

    override fun register(email: String, password: String) {
        interactor!!.register(email, password)
    }

    override fun toast(text: Int) {
        view.toast(text)
    }

    override fun goMain() {
        view.goMain()
    }
}