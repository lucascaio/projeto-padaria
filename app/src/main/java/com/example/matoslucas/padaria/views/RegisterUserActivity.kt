package com.example.matoslucas.padaria.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.matoslucas.padaria.R
import com.example.matoslucas.padaria.business.UserBusiness
import com.example.matoslucas.padaria.repository.UserRepository
import com.example.matoslucas.padaria.util.ValidationException
import kotlinx.android.synthetic.main.activity_register_user.*

class RegisterUserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mUserBusiness: UserBusiness

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        //EVENTS
        setListeners()

        mUserBusiness = UserBusiness(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonSave -> {
                handleSave()
            }
        }
    }

    private fun setListeners() {
        buttonSave.setOnClickListener(this)
    }

    private fun handleSave() {
        try {
            val name = editName.text.toString()
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()

            //Insert de user
            mUserBusiness.insert(name, email, password)
            startActivity(Intent(this, MainActivity::class.java))
            finish()

        } catch (e: ValidationException) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Erro, entre em contato com o suporte.", Toast.LENGTH_LONG).show()
        }

    }
}
