package android.skills_market.network.models.responses

import android.skills_market.network.models.User
import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("accessToken"  ) var accessToken  : String? = null,
    @SerializedName("refreshToken" ) var refreshToken : String? = null,
    @SerializedName("user"         ) var user         : User?   = User()
)
