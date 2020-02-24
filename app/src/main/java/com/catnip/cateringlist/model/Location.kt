package com.catnip.cateringlist.model

import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

data class Location(
    @SerializedName("secure_id")
    var id: String,
    @SerializedName("name")
    var name: String
)

data class Locations(
    @SerializedName("data")
    var locations: List<Location>
)

