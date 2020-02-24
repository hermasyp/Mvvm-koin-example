package com.catnip.cateringlist.base

import android.app.Application
import android.content.Context
import com.catnip.cateringlist.di.networkModule
import com.catnip.cateringlist.di.repositories
import com.catnip.cateringlist.di.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class CateringApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CateringApp)
            modules(listOf(networkModule, viewModels, repositories))
        }
    }
}