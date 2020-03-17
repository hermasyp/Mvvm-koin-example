package com.catnip.cateringlist.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.catnip.cateringlist.model.Caterings
import com.catnip.cateringlist.utils.result.ResultState

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class MainViewModel(val repository: MainRepository) : ViewModel() {
    val catering : LiveData<ResultState<Caterings>> =
        repository.cateringLiveData


    fun getCateringData(){
        repository.fetchCatering()
    }


    override fun onCleared() {
        super.onCleared()
        repository.destroy()
    }

}