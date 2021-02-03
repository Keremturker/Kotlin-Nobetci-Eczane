package com.keremturker.kotlineczane.service

import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
class EczaneAPIService {


    // https://api.collectapi.com/health/dutyPharmacy?ilce=%C3%87ankaya&il=Ankara'
    //BASE URL =>  https://api.collectapi.com/
    //Ext => health/dutyPharmacy?ilce=%C3%87ankaya&il=Ankara'

    private val BASE_URL = "https://api.collectapi.com/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(EczaneAPI::class.java)


    fun getData(il: String, ilce: String): Single<JsonObject> {

        return api.getEczane(il, ilce)

    }
}