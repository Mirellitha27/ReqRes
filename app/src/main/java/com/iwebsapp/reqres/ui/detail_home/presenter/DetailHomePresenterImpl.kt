package com.iwebsapp.reqres.ui.detail_home.presenter

import com.iwebsapp.reqres.ui.detail_home.interactor.DetailHomeInteractor
import com.iwebsapp.reqres.ui.detail_home.interactor.DetailHomeInteractorImpl
import com.iwebsapp.reqres.ui.detail_home.view.DetailHomeView

class DetailHomePresenterImpl(private var view: DetailHomeView): DetailHomePresenter {
    private var interactor: DetailHomeInteractor? = null

    init {
        this.interactor = DetailHomeInteractorImpl(this)
    }


    override fun init(id: String) {
        interactor!!.init(id)
    }

    override fun setData(
        id: String,
        email: String?,
        name: String?,
        lastName: String?,
        image: String?
    ) {
        view.setData(id, email, name, lastName, image)
    }
}