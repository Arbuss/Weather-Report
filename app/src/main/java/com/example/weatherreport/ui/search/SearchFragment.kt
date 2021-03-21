package com.example.weatherreport.ui.search

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.weatherreport.App
import com.example.weatherreport.R
import com.example.weatherreport.data.weather.Weather
import com.example.weatherreport.ui.dialog.WeatherDialog
import kotlinx.android.synthetic.main.fragment_search.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class SearchFragment : MvpAppCompatFragment(R.layout.fragment_search), SearchView {

    @InjectPresenter
    lateinit var presenter: SearchPresenter

    @ProvidePresenter
    fun provide() = SearchPresenter().also {
        (activity?.applicationContext as App).appComponent.inject(it)
    }

    override fun onAttach(context: Context) {
        (activity?.applicationContext as App).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindSearchButton()
    }

    override fun onSuccess(weather: Weather) {
        WeatherDialog(weather).show(childFragmentManager, "")
    }

    override fun onError() {
        AlertDialog.Builder(requireContext()).setMessage(R.string.search_screen_connection_error).show()
    }

    private fun bindSearchButton() {
        searchButton.setOnClickListener {
            presenter.search(
                searchView.query.toString(),
                getString(R.string.weather_units),
                getString(R.string.weather_lang)
            )
        }
    }
}