package com.catnip.cateringlist.di

import com.catnip.cateringlist.feature.location.LocationRepository
import com.catnip.cateringlist.feature.location.LocationViewModel
import com.catnip.cateringlist.feature.main.MainRepository
import com.catnip.cateringlist.feature.main.MainViewModel
import com.catnip.cateringlist.network.RetrofitApi
import com.catnip.cateringlist.utils.rx.AppScheduler
import io.reactivex.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

val networkModule = module {
    single { RetrofitApi() }
    single { AppScheduler() }
    factory { CompositeDisposable() }
}
val viewModels = module {
    viewModel { MainViewModel(get()) }
    viewModel { LocationViewModel(get()) }
}

val repositories = module {
    single { MainRepository(get(), get(), get()) }
    single { LocationRepository(get(), get(), get()) }
}
