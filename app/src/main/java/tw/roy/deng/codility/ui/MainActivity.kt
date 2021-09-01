package tw.roy.deng.codility.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import dagger.android.support.DaggerAppCompatActivity
import tw.roy.deng.codility.R
import tw.roy.deng.codility.databinding.ActivityMainBinding
import tw.roy.deng.codility.utils.setupWithNavController

class MainActivity : DaggerAppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val bottomNavigationView = binding.bottomNavBar
        val navGraphIds = listOf(
            R.navigation.nav_users
        )
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.mainNavFragment,
            intent = intent
        )

        currentNavController = controller
    }

}