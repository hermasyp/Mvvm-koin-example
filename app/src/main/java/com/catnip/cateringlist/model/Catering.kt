package com.catnip.cateringlist.model

import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

data class Catering(
    @SerializedName("caterer_logo")
    var catererLogo: String,
    @SerializedName("caterer_name")
    var catererName: String,
    @SerializedName("caterer_secure_id")
    var catererSecureId: String,
    @SerializedName("distance")
    var distance: String,
    @SerializedName("kitchen_secure_id")
    var kitchenSecureId: String,
    @SerializedName("rating")
    var rating: Double,
    @SerializedName("tier_icon")
    var tierIcon: String,
    @SerializedName("tier_name")
    var tierName: String,
    @SerializedName("tier_secure_id")
    var tierSecureId: String
)

data class Caterings(
    @SerializedName("data")
    var caterings : List<Catering>
)

