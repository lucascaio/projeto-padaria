package com.example.matoslucas.padaria.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.example.matoslucas.padaria.R
import com.example.matoslucas.padaria.constants.PadariaConstants
import com.example.matoslucas.padaria.util.SecurityPreferences

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mSecurityPreferences: SecurityPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)

        //Instancia Variaveis
        mSecurityPreferences = SecurityPreferences(this)
        startDefaultFragment()
    }


    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        var fragment: Fragment? = null
        val id = item.itemId

        when (id) {
            R.id.nav_cadastro_fornada -> fragment = FornadaListFragment.newInstance()
            R.id.nav_gallery -> fragment = FornadaListFragment.newInstance()
            R.id.nav_logout -> handleLogout()
        }

        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.frameContent, fragment).commit()

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun startDefaultFragment() {
        val fragment: Fragment? = FornadaListFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.frameContent, fragment).commit()


    }

    private fun handleLogout() {

        //Limpar dados do usuario
        mSecurityPreferences.removeStoreString(PadariaConstants.KEY.USER_ID)
        mSecurityPreferences.removeStoreString(PadariaConstants.KEY.USER_NAME)
        mSecurityPreferences.removeStoreString(PadariaConstants.KEY.USER_EMAIL)

        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
