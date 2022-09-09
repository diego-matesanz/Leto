package com.dmatesanz.leto.screens.authentication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmatesanz.leto.screens.MainActivity
import com.google.firebase.auth.FirebaseAuth

class AuthenticationViewModel: ViewModel() {

    private lateinit var auth: FirebaseAuth

    val createUserSuccess: MutableLiveData<Boolean> = MutableLiveData(false)
    val loginSuccess: MutableLiveData<Boolean> = MutableLiveData(false)

    fun initAuth() {
        auth = FirebaseAuth.getInstance()
    }

    fun createUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(MainActivity.instance) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null) {
                        createUserSuccess.value = true
                    }
                } else {
                    createUserSuccess.value = false
                }
            }
    }
}
