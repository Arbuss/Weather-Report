package com.example.weatherreport.ui.login

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.weatherreport.App
import com.example.weatherreport.R
import com.github.terrakok.cicerone.Router
import kotlinx.android.synthetic.main.fragment_login.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class LoginFragment : Fragment(R.layout.fragment_login), LoginView {

    @Inject
    @InjectPresenter
    lateinit var presenter: LoginPresenter

    @Inject
    lateinit var router: Router

    override fun onAttach(context: Context) {
        (activity?.applicationContext as App).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signInButton.setOnClickListener {
            presenter.signIn(
                login.text.toString(),
                password.text.toString()
            )
        }
    }

    override fun highlightLoginField() {
        login.error = getString(R.string.login_screen_empty_login)
    }

    override fun highlightPasswordField() {
        password.error = getString(R.string.login_screen_empty_password)
    }
}