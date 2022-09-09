package com.dmatesanz.leto.utils

import android.text.TextUtils

object ExtensionFunctions {

    fun String.isValidEmail(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }
}
