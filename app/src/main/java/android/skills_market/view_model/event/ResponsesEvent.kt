package android.skills_market.view_model.event

import android.skills_market.data.network.models.VacancyModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

sealed interface ResponsesEvent {
    data class AddChat(
        val vacancyId: String,
        val companyId: String,
        val studentId: String = Firebase.auth.currentUser!!.uid
    ):ResponsesEvent
}