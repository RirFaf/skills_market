package android.skills_market.network.models.requests

import com.google.gson.annotations.SerializedName

data class AuthRequest (
    @SerializedName("email"    ) var email    : String? = null,
    @SerializedName("password" ) var password : String? = null
)