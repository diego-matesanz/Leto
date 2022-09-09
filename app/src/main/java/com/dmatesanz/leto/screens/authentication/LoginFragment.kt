package com.dmatesanz.leto.screens.authentication

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
import com.dmatesanz.leto.R
import com.dmatesanz.leto.databinding.FragmentLoginBinding
import com.dmatesanz.leto.utils.ExtensionFunctions.isValidEmail

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: AuthenticationViewModel by activityViewModels()

    private var isEmailEmpty: Boolean = true
    private var isValidEmail: Boolean = false
    private var isPasswordEmpty: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)

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
        binding.editTextEmail.doOnTextChanged { text, _, _, _ ->
            binding.textInputLayoutEmail.error = null
            isValidEmail = text.toString().isValidEmail()
            isEmailEmpty = text.isNullOrEmpty()
        }
        binding.editTextPassword.doOnTextChanged { text, _, _, _ ->
            binding.textInputLayoutPassword.error = null
            isPasswordEmpty = text.isNullOrEmpty()

        }
        binding.buttonLogin.setOnClickListener {
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
    }

    private fun initSignUpSpan() {
        val noAccountSpannable = SpannableString(resources.getString(R.string.no_account))
        val signUpText = resources.getString(R.string.sign_up)
        val signUpTextColor = resources.getColor(R.color.tart_orange, null)
        val signUpClickListener = object : ClickableSpan() {
            override fun onClick(p0: View) {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
        val startPosition = noAccountSpannable.toString().indexOf(signUpText, 0, false)
        val endPosition = startPosition + signUpText.length
        noAccountSpannable.setSpan(signUpClickListener, startPosition, endPosition, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        noAccountSpannable.setSpan(
            ForegroundColorSpan(signUpTextColor), startPosition, endPosition, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.textViewNoAccount.text = noAccountSpannable
        binding.textViewNoAccount.movementMethod = LinkMovementMethod.getInstance()
    }
}
