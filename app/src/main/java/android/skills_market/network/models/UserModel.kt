package android.skills_market.network.models

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("email") var email: String? = null,
    @SerializedName("password") var password: String? = null
)