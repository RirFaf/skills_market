package android.skills_market.db_functions

import java.util.regex.Pattern

fun isEmailValid(email: String): Boolean {
    val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"
    )
    return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
}