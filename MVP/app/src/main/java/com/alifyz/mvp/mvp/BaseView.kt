package com.alifyz.mvp.mvp

interface BaseView<T> {
    var presenter : T
    fun bindViews()
}