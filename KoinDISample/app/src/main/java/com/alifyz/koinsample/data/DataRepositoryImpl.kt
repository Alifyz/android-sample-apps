package com.alifyz.koinsample.data

import com.alifyz.koinsample.model.Currency
import com.google.gson.Gson

class DataRepositoryImpl() {

    val gson = Gson()

    fun getCurrencies(jsonString : String) : List<Currency> {
        return gson.fromJson(jsonString, Array<Currency>::class.java).toList()
    }
}