package com.example.matoslucas.padaria.business

import android.content.Context
import com.example.matoslucas.padaria.entities.FornadaEntity
import com.example.matoslucas.padaria.repository.FornadaRepository

class FornadaBusiness(context: Context) {

    private val mFornadaRepository: FornadaRepository = FornadaRepository.getInstance(context)
    fun getList(): MutableList<FornadaEntity> = mFornadaRepository.getList()
}
