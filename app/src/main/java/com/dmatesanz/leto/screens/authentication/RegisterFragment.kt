package com.dmatesanz.leto.screens.authentication

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dmatesanz.leto.R
import com.dmatesanz.leto.databinding.FragmentRegisterBinding
import com.dmatesanz.leto.utils.ExtensionFunctions.isValidEmail
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var auth: FirebaseAuth

    private val viewModel: AuthenticationViewModel by activityViewModels()

    private var isEmailEmpty: Boolean = true
    private var isValidEmail: Boolean = false
    private var isPasswordEmpty: Boolean = true
    private var isRepeatPasswordEmpty: Boolean = true
    private var isRepeatPasswordValid: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()

        setListeners()
        initSignUpSpan()

        return binding.root
    }

    private fun setListeners() {
        binding.editTextEmail.setOnFocusChangeListener { _, focus ->
            val hint = if (focus) {
                null
            } else {
                if (isEmailEmpty) resources.getString(R.string.email_address) else null
            }
            binding.textInputLayoutEmail.hint = hint
        }
        binding.editTextPassword.setOnFocusChangeListener { _, focus ->
            val hint = if (focus) {
                null
            } else {
                if (isPasswordEmpty) resources.getString(R.string.password) else null
            }
            binding.textInputLayoutPassword.hint = hint
        }
        binding.editTextRepeatPassword.setOnFocusChangeListener { _, focus ->
            val hint = if (focus) {
                null
            } else {
                if (isRepeatPasswordEmpty) resources.getString(R.string.repeat_password) else null
            }
            binding.textInputLayoutRepeatPassword.hint = hint
        }
        binding.editTextEmail.doOnTextChanged { text, _, _, _ ->
            binding.textInputLayoutEmail.error = null
            isValidEmail = text.toString().isValidEmail()
            isEmailEmpty = text.isNullOrEmpty()
        }
        binding.editTextPassword.doOnTextChanged { text, _, _, _ ->
            binding.textInputLayoutPassword.error = null
            isPasswordEmpty = text.isNullOrEmpty()
        }
        binding.editTextRepeatPassword.doOnTextChanged { text, _, _, _ ->
            binding.textInputLayoutRepeatPassword.error = null
            isRepeatPasswordEmpty = text.isNullOrEmpty()
            if (text != null) {
                isRepeatPasswordValid = text.toString() == binding.editTextPassword.text.toString()
            }
        }
        binding.buttonRegister.setOnClickListener {
            checkForm()
        }
    }

    private fun checkForm() {
        val emailValidity = isCorrectEmail()
        val passwordValidity = isCorrectPassword()
        val repeatPasswordValidity = isCorrectRepeatPassword()
        if (emailValidity && passwordValidity && repeatPasswordValidity) {
            auth.createUserWithEmailAndPassword(
                binding.editTextEmail.text.toString(),
                binding.editTextPassword.text.toString()
            )
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        if (user != null) {
                            Log.d(TAG, user.uid)
                        }
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    }
                }
        }
    }

    private fun isCorrectEmail(): Boolean {
        val emailError =
            if (isEmailEmpty) resources.getString(R.string.is_empty) else if (!isValidEmail) resources.getString(R.string.format_error) else null
        binding.textInputLayoutEmail.error = emailError
        return emailError.isNullOrEmpty()
    }

    private fun isCorrectPassword(): Boolean {
        val passwordError =
            if (isPasswordEmpty) resources.getString(R.string.is_empty) else null
        binding.textInputLayoutPassword.error = passwordError
        return passwordError.isNullOrEmpty()
    }

    private fun isCorrectRepeatPassword(): Boolean {
        val repeatPasswordError =
            if (isRepeatPasswordEmpty) resources.getString(R.string.is_empty) else if (!isRepeatPasswordValid)
                resources.getString(R.string.password_match_error) else null
        binding.textInputLayoutRepeatPassword.error = repeatPasswordError
        return repeatPasswordError.isNullOrEmpty()
    }

    private fun initSignUpSpan() {
        val haveAccountSpannable = SpannableString(resources.getString(R.string.have_account))
        val loginText = resources.getString(R.string.login)
        val loginTextColor = resources.getColor(R.color.tart_orange, null)
        val loginClickListener = object : ClickableSpan() {
            override fun onClick(p0: View) {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }
        val startPosition = haveAccountSpannable.toString().indexOf(loginText, 0, false)
        val endPosition = startPosition + loginText.length
        haveAccountSpannable.setSpan(loginClickListener, startPosition, endPosition, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        haveAccountSpannable.setSpan(
            ForegroundColorSpan(loginTextColor), startPosition, endPosition, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.textViewHaveAccount.text = haveAccountSpannable
        binding.textViewHaveAccount.movementMethod = LinkMovementMethod.getInstance()
    }

    companion object {
        private val TAG = RegisterFragment::class.java.simpleName
    }
}
