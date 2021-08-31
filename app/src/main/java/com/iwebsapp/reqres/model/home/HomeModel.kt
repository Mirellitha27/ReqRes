package com.iwebsapp.reqres.model.home

data class HomeModel(
    var page: Int? = null,
    var per_page: Int? = null,
    var total: Int? = null,
    var total_pages: Int? = null,
    var data: List<Datum>
)