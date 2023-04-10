package com.dmatesanz.leto.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.dmatesanz.leto.screens.MainActivity

object PhoneUtil {

    fun isKeyBoardShowing(): Boolean {
        val inm by lazy { MainActivity.instance.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }
        val windowHeightMethod = InputMethodManager::class.java.getMethod("getInputMethodWindowVisibleHeight")
        val height = windowHeightMethod.invoke(inm) as Int
        return height > 0
    }
}
