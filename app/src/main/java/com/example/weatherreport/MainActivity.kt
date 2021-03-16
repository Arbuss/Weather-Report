 package com.example.weatherreport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherreport.navigation.Screens
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

 class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject lateinit var navigatorHolder: NavigatorHolder
    @Inject lateinit var router: Router
    private val navigator = AppNavigator(this, R.id.fragmentContainer)

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        navigatorHolder.setNavigator(navigator)
        router.navigateTo(Screens.login())
    }
}