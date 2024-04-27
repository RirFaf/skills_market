package android.skills_market.data.network.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email"       ) var email       : String?  = null,
    @SerializedName("id"          ) var id          : String?  = null,
    @SerializedName("role"        ) var role        : String?  = null,
    @SerializedName("password"    ) var password    : String?  = null,
    @SerializedName("isActivated" ) var isActivated : Boolean? = null
)
