package com.catnip.cateringlist.feature.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.catnip.cateringlist.model.Locations
import com.catnip.cateringlist.utils.result.ResultState

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class LocationViewModel (val repository: LocationRepository) : ViewModel() {
    val locations : LiveData<ResultState<Locations>> by lazy {
        repository.locationsLiveData
    }

    fun getLocationData(){
        repository.fetchCatering()
    }


    override fun onCleared() {
        super.onCleared()
        repository.destroy()
    }

}