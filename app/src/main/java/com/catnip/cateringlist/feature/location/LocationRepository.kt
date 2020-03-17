package com.catnip.cateringlist.feature.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.catnip.cateringlist.model.Catering
import com.catnip.cateringlist.model.Caterings
import com.catnip.cateringlist.model.Locations
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

class LocationRepository(
    private val api: RetrofitApi,
    private val scheduler: AppScheduler,
    private val compositeDisposable: CompositeDisposable
) {

    val locationsLiveData = MutableLiveData<ResultState<Locations>>()

    fun fetchCatering() {
        locationsLiveData.value = ResultState.loading(true)
        api.getCateringLocation()
            .performOnBackOutOnMain(scheduler)
            .subscribe(
                {
                    locationsLiveData.value = ResultState.success(it.data)

                },
                {
                    locationsLiveData.value = ResultState.error(it)

                }
            ).addTo(compositeDisposable)
    }


    fun destroy() {
        compositeDisposable.clear()
    }

}

