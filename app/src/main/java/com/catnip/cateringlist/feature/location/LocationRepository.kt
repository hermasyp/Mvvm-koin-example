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

    private val locationsData: PublishSubject<ResultState<Locations>> =
        PublishSubject.create<ResultState<Locations>>()

    val locationsLiveData: LiveData<ResultState<Locations>> by lazy {
        locationsData.toLiveData(compositeDisposable)
    }

    fun fetchCatering() {
        locationsData.loading(true)
        api.getCateringLocation()
            .performOnBackOutOnMain(scheduler)
            .subscribe(
                {
                    locationsData.success(it.data)
                },
                {
                    locationsData.error(it)
                }
            ).addTo(compositeDisposable)
    }


    fun destroy() {
        compositeDisposable.dispose()
    }

}

