package com.example.matoslucas.padaria.business

import android.content.Context
import com.example.matoslucas.padaria.constants.PadariaConstants
import com.example.matoslucas.padaria.entities.UserEntity
import com.example.matoslucas.padaria.repository.UserRepository
import com.example.matoslucas.padaria.util.SecurityPreferences
import com.example.matoslucas.padaria.util.ValidationException

class UserBusiness(var context: Context) {
    private val mUserRepository: UserRepository = UserRepository.getInstance(context)
    private val mSecurityPreferences: SecurityPreferences = SecurityPreferences(context)

    fun login(email: String, password: String): Boolean{
        val user: UserEntity? = mUserRepository.get(email, password)
        return if(user!= null){
            mSecurityPreferences.storeString(PadariaConstants.KEY.USER_ID, user.id.toString())
            mSecurityPreferences.storeString(PadariaConstants.KEY.USER_NAME, user.name)
            mSecurityPreferences.storeString(PadariaConstants.KEY.USER_EMAIL, user.email)
             true
        }else{
             false
        }

    }

    fun insert(name: String, email: String, password: String) {
        try {
            if (name == "" || email == "" || password == "") {
                throw ValidationException("Informe todos os campos!")
            }
            if (mUserRepository.isEmailExistent(email)) {
                throw ValidationException("Email já cadastrado!")
            }
            val userId = mUserRepository.insert(name, email, password)

            //Salvar dados do usuário no shared
            mSecurityPreferences.storeString(PadariaConstants.KEY.USER_ID, userId.toString())
            mSecurityPreferences.storeString(PadariaConstants.KEY.USER_NAME, name)
            mSecurityPreferences.storeString(PadariaConstants.KEY.USER_EMAIL, email)

        } catch (e: Exception) {
            throw e
        }

    }
}
