package com.catnip.cateringlist.model

import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

data class ResponseResult<T>(
    @SerializedName("result")
    var data: T
)