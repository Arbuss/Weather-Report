package com.example.weatherreport.ui.register

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.weatherreport.App
import com.example.weatherreport.Const
import com.example.weatherreport.R
import com.example.weatherreport.ui.login.LoginPresenter
import kotlinx.android.synthetic.main.fragment_register.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class RegisterFragment() : Fragment(R.layout.fragment_register), RegisterView {

    constructor(login: String, password: String): this() {
        arguments = Bundle().also {
            it.putString(Const.RegisterScreen.LOGIN_KEY, login)
            it.putString(Const.RegisterScreen.PASSWORD_KEY, password)
        }
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: RegisterPresenter

    override fun onAttach(context: Context) {
        (activity?.applicationContext as App).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindRegisterButton()
        fillFields()
    }

    override fun highlightPasswordConfirmationField() {
        passwordConfirmation.error = getText(R.string.login_screen_empty_password)
    }

    override fun highlightPasswordsFields() {
        password.error = getText(R.string.register_screen_password_mismatching)
        passwordConfirmation.error = getText(R.string.register_screen_password_mismatching)
    }

    private fun bindRegisterButton() {
        registerButton.setOnClickListener {
            presenter.register(
                login.text.toString(),
                password.text.toString(),
                passwordConfirmation.text.toString()
            )
        }
    }

    private fun fillFields() {
        arguments?.let {
            login.setText(it.getString(Const.RegisterScreen.LOGIN_KEY, ""))
            password.setText(it.getString(Const.RegisterScreen.PASSWORD_KEY, ""))
        }
    }
}