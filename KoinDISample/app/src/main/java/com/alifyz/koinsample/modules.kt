package com.alifyz.koinsample

import com.alifyz.koinsample.data.DataRepositoryImpl
import com.google.gson.Gson
import org.koin.dsl.module.module

val applicationModule = module {
    single { Gson() }
    factory { CurrencyAdapter() }
    factory { DataRepositoryImpl() }
}