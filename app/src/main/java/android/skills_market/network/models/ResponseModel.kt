package android.skills_market.network.models

import androidx.annotation.DrawableRes

data class ResponseModel(
    @DrawableRes val imageId: Int,
    val title: String,
    val companyName: String
)