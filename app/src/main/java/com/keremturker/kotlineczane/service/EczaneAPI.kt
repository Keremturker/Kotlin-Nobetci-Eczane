package com.keremturker.kotlineczane.service

import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface EczaneAPI {


    // https://api.collectapi.com/health/dutyPharmacy?ilce=%C3%87ankaya&il=Ankara'
    //BASE URL =>  https://api.collectapi.com/
    //Ext => health/dutyPharmacy?ilce=%C3%87ankaya&il=Ankara'

    @Headers(
        "content-type: application/json",
        "authorization: apikey 2TlWTQiWUkWQRUmaIrtBIq:3Bw5Dv3oXzRznLroklhs2h"
    )
    @GET("health/dutyPharmacy")
    fun getEczane(
        @Query("il") il: String,
        @Query("ilce") ilce: String
    ): Single<JsonObject>
}