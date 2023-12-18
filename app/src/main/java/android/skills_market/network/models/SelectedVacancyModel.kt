package android.skills_market.network.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SelectedVacancyModel(
    val id: Int = 0,
    val position: String,
    val salary: Int,
    val companyName: String,
    val edArea: String,
    val formOfEmployment: String,
    val requirements: String,
    val location: String,
    val about: String,
) : Parcelable