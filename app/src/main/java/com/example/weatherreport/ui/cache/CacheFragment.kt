package com.example.weatherreport.ui.cache

import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.weatherreport.App
import com.example.weatherreport.R
import com.example.weatherreport.data.weather.Weather
import com.example.weatherreport.ui.dialog.WeatherDialog
import kotlinx.android.synthetic.main.fragment_list.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class CacheFragment : MvpAppCompatFragment(R.layout.fragment_list), CacheView {
    @InjectPresenter
    lateinit var presenter: CachePresenter

    @ProvidePresenter
    fun provide() = CachePresenter().also {
        (activity?.applicationContext as App).appComponent.inject(it)
    }

    override fun onAttach(context: Context) {
        (activity?.applicationContext as App).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.initAdapter()
    }

    override fun initAdapter(items: List<Weather>) {
        recyclerView?.adapter = WeatherAdapter(
            items.toMutableList()
        ) {
            WeatherDialog(it).show(childFragmentManager, "")
        }
    }

    override fun fillAdapter(items: List<Weather>) {
        (recyclerView?.adapter as? WeatherAdapter)?.updateItems(items)
    }

    override fun onResume() {
        super.onResume()
        presenter.updateAdapter()
    }
}