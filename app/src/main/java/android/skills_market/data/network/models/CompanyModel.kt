package android.skills_market.data.network.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CompanyModel(
    val id: String,
    val name: String
) : Parcelable