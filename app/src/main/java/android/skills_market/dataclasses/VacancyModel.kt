package android.skills_market.dataclasses

import androidx.annotation.DrawableRes

data class VacancyModel(
    @DrawableRes val imageId:Int,
    val title:String,
    val companyName:String
)
