package com.example.videogamelist.screens.authentication

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.videogamelist.R
import com.example.videogamelist.databinding.FragmentRegisterBinding
import com.example.videogamelist.utils.ExtensionFunctions.isValidEmail

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

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
            checkCredentials()
        }
    }

    private fun checkCredentials() {
        val emailError =
            if (isEmailEmpty) resources.getString(R.string.is_empty) else if (!isValidEmail) resources.getString(R.string.format_error) else null
        binding.textInputLayoutEmail.error = emailError
        val passwordError =
            if (isPasswordEmpty) resources.getString(R.string.is_empty) else null
        binding.textInputLayoutPassword.error = passwordError
        val repeatPasswordError =
            if (isRepeatPasswordEmpty) resources.getString(R.string.is_empty) else if (!isRepeatPasswordValid)
                resources.getString(R.string.password_match_error) else null
        binding.textInputLayoutRepeatPassword.error = repeatPasswordError
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
}
