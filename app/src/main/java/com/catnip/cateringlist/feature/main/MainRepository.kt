package com.catnip.cateringlist.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.catnip.cateringlist.model.Catering
import com.catnip.cateringlist.model.Caterings
import com.catnip.cateringlist.network.RetrofitApi
import com.catnip.cateringlist.utils.result.*
import com.catnip.cateringlist.utils.rx.AppScheduler
import com.catnip.cateringlist.utils.rx.addTo
import com.catnip.cateringlist.utils.rx.performOnBackOutOnMain
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import retrofit2.Response

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

class MainRepository(
    private val api: RetrofitApi,
    private val scheduler: AppScheduler,
    private val compositeDisposable: CompositeDisposable
) {
    val cateringLiveData = MutableLiveData<ResultState<Caterings>>()
    fun fetchCatering() {
        cateringLiveData.value = ResultState.loading(true)
        api.getCateringList()
            .performOnBackOutOnMain(scheduler)
            .subscribe(
                {
                    cateringLiveData.value = ResultState.success(it.data)
                },
                {
                    cateringLiveData.value = ResultState.error(it)

                }
            ).addTo(compositeDisposable)
    }


    fun destroy() {
        compositeDisposable.clear()
    }

}

