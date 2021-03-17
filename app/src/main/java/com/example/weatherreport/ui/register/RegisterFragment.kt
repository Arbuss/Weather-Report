package com.example.weatherreport.ui.register

import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.weatherreport.App
import com.example.weatherreport.Const
import com.example.weatherreport.R
import kotlinx.android.synthetic.main.fragment_register.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class RegisterFragment() : MvpAppCompatFragment(R.layout.fragment_register), RegisterView {

    constructor(login: String, password: String) : this() {
        arguments = Bundle().also {
            it.putString(Const.RegisterScreen.LOGIN_KEY, login)
            it.putString(Const.RegisterScreen.PASSWORD_KEY, password)
        }
    }

    @InjectPresenter
    lateinit var presenter: RegisterPresenter

    @ProvidePresenter
    fun provide() = RegisterPresenter().also {
        (activity?.applicationContext as App).appComponent.inject(it)
    }

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