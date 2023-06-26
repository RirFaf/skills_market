package android.skills_market.view_models.states

import android.telephony.mbms.MbmsErrors.StreamingErrors

data class LoginUIState(
    val isPasswordNull: Boolean = true,
    val isPasswordCorrect: Boolean = false,
    val isLoginNull: Boolean = true,
    val isLoginCorrect: Boolean = false,
    val login: String = "",
    val password: String = "",
)