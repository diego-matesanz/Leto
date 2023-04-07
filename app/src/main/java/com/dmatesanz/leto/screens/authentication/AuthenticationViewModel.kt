package com.dmatesanz.leto.screens.authentication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmatesanz.leto.screens.MainActivity
import com.google.firebase.auth.FirebaseAuth

class AuthenticationViewModel: ViewModel() {

    private lateinit var auth: FirebaseAuth

    val loginSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val createUserSuccess: MutableLiveData<Boolean> = MutableLiveData()

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

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(MainActivity.instance) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null) {
                        loginSuccess.value = true
                    }
                } else {
                    loginSuccess.value = false
                }
            }
    }
}
