package com.iwebsapp.reqres

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.iwebsapp.reqres.databinding.ActivityMainBinding
import com.iwebsapp.reqres.ui.login.view.LoginActivity
import com.iwebsapp.reqres.utils.UserSession

class MainActivity : AppCompatActivity() {
    private lateinit var textViewEmail: TextView
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val sigOffItem: MenuItem = navView.menu.findItem(R.id.sign_off)
        sigOffItem.setOnMenuItemClickListener { item: MenuItem? ->
            goLogin()
            true
        }


        val header: View = navView.getHeaderView(0)
        textViewEmail = header.findViewById(R.id.textViewEmail)
        val userSession = UserSession(this)
        textViewEmail.text = userSession.getEmial()
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun goLogin() {
        val userSession = UserSession(this)
        userSession.logout()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}