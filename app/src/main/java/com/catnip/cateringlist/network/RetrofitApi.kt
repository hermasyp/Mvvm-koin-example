package com.catnip.cateringlist.network

import com.catnip.cateringlist.BuildConfig
import com.catnip.cateringlist.model.Caterings
import com.catnip.cateringlist.model.Locations
import com.catnip.cateringlist.model.ResponseResult
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

interface RetrofitApi {
    //TODO : api interface will written in here.

    @GET("5e44fe6e3000005d3561474e")
    fun getCateringLocation(): Single<ResponseResult<Locations>>

    @GET("5e450611300000543c614786")
    fun getCateringList(): Single<ResponseResult<Caterings>>

    companion object {
        operator fun invoke(): RetrofitApi {
            val client = UnsafeOkhttpCreator.getClient()
                .newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RetrofitApi::class.java)
        }
    }
}