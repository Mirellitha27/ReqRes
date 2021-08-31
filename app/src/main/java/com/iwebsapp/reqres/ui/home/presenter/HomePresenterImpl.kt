package com.iwebsapp.reqres.ui.home.presenter

import android.widget.ImageView
import com.iwebsapp.reqres.model.home.Datum
import com.iwebsapp.reqres.ui.home.interactor.HomeInteractor
import com.iwebsapp.reqres.ui.home.interactor.HomeInteractorImpl
import com.iwebsapp.reqres.ui.home.view.HomeView

class HomePresenterImpl(private var view: HomeView?) : HomePresenter {
    private var interactor: HomeInteractor? = null

    init {
        this.interactor = HomeInteractorImpl(this)
    }

    override fun init() {
        interactor?.init()
    }

    override fun setDataAdapter(data: ArrayList<Datum>) {
        view!!.setDataAdapter(data)
    }

    override fun showImage(urlImg: String?, imageView: ImageView?) {
        view!!.showImage(urlImg, imageView)
    }

    override fun showDetail(idUser: String) {
        view!!.showDetail(idUser)
    }

}