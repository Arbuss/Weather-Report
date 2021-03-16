package com.example.weatherreport.ui.register

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.weatherreport.App
import com.example.weatherreport.R
import kotlinx.android.synthetic.main.fragment_register.*
import javax.inject.Inject

class RegisterFragment : Fragment(R.layout.fragment_register), RegisterView {

    @Inject
    lateinit var presenter: RegisterPresenter

    override fun onAttach(context: Context) {
        (context as App).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerButton.setOnClickListener {
            presenter.register(
                login.text.toString(),
                password.text.toString(),
                passwordConfirmation.text.toString()
            )
        }
    }

    override fun highlightPasswordConfirmationField() {
        passwordConfirmation.error = getText(R.string.login_screen_empty_password)
    }

    override fun highlightPasswordsFields() {
        password.error = getText(R.string.register_screen_password_mismatching)
        passwordConfirmation.error = getText(R.string.register_screen_password_mismatching)
    }
}