package com.keremturker.kotlineczane.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.keremturker.kotlineczane.R
import com.keremturker.kotlineczane.model.Eczane
import com.keremturker.kotlineczane.service.EczaneAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel : ViewModel() {

    val eczaneList = MutableLiveData<List<Eczane>>()
    val sehirList = MutableLiveData<Array<String>>()
    val eczaneLoading = MutableLiveData<Boolean>()
    val eczaneError = MutableLiveData<Boolean>()


    private val eczaneAPIService = EczaneAPIService()

    private val disposable = CompositeDisposable()


    fun getData(il: String, ilce: String) {

        eczaneLoading.value = true


        disposable.add(

            eczaneAPIService.getData(il, ilce)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<JsonObject>() {
                    override fun onSuccess(t: JsonObject) {


                        var eczaneJarray = t.getAsJsonArray("result")


                        eczaneList.value =
                            Gson().fromJson(eczaneJarray, Array<Eczane>::class.java)
                                .toList()
                        eczaneLoading.value = false
                        eczaneError.value = false

                    }

                    override fun onError(e: Throwable) {

                        e.printStackTrace()
                        eczaneLoading.value = false
                        eczaneError.value = true
                    }


                })

        )


    }

    fun addSehir(context: Context?) {

        context?.let {

            sehirList.value = it.resources.getStringArray(R.array.sehir_list)
        }


    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}