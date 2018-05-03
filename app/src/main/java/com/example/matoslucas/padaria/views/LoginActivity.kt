package com.example.matoslucas.padaria.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.matoslucas.padaria.R
import com.example.matoslucas.padaria.business.UserBusiness
import com.example.matoslucas.padaria.constants.PadariaConstants
import com.example.matoslucas.padaria.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mUserBusiness: UserBusiness
    private lateinit var mSecurityPreferences: SecurityPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //Instanciar as variaveis da classe
        mUserBusiness = UserBusiness(this)
        mSecurityPreferences = SecurityPreferences(this)
        setListeners()
        verifyLoggedUser()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonLogin -> {
                handleLogin()
            }
        }
    }

    private fun verifyLoggedUser() {
        val userId = mSecurityPreferences.getStoreString(PadariaConstants.KEY.USER_ID)
        val userName = mSecurityPreferences.getStoreString(PadariaConstants.KEY.USER_NAME)
        if (userId != "" && userName != "") {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun setListeners() {
        buttonLogin.setOnClickListener(this)
    }

    private fun handleLogin() {
        val email = editEmail.text.toString()
        val password = editPassword.text.toString()
        if (mUserBusiness.login(email, password)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Usuário ou senha incorretos!", Toast.LENGTH_LONG).show()
        }


    }
}
