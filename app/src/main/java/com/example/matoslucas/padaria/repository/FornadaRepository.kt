package com.example.matoslucas.padaria.repository

import android.content.Context
import android.database.Cursor
import com.example.matoslucas.padaria.constants.DataBaseConstants
import com.example.matoslucas.padaria.entities.FornadaEntity
import com.example.matoslucas.padaria.entities.UserEntity

class FornadaRepository private constructor(context: Context){
    private var mFPadariaDatabaseHelper: PadariaDatabaseHelper = PadariaDatabaseHelper(context)

    companion object {
        fun getInstance(context: Context): FornadaRepository {
            if (INSTANCE == null) {
                INSTANCE = FornadaRepository(context)
            }
            return INSTANCE as FornadaRepository
        }

        private var INSTANCE: FornadaRepository? = null
    }
//TODO Terminar 2:23
//    fun get(): FornadaEntity? {
//        var fornadaEntity : FornadaEntity? = null
//        try {
//            val cursor: Cursor
//            val db = mFPadariaDatabaseHelper.readableDatabase
//
//            val projection = arrayOf(DataBaseConstants.FORNADA.COLUMNS.ID,
//                    DataBaseConstants.FORNADA.COLUMNS.DESCRIPTION,
//                    DataBaseConstants.FORNADA.COLUMNS.DATEFINISH)
//
//            val selection = "${DataBaseConstants.USER.COLUMNS.EMAIL} = ? AND ${DataBaseConstants.USER.COLUMNS.PASSWORD} = ?"
//            //val selectionArgs = arrayOf(email, password)
//            cursor = db.query(DataBaseConstants.USER.TABLE_NAME, projection, selection, selectionArgs, null, null, null)
//            if(cursor.count > 0){
//                cursor.moveToFirst()
//                val userId = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.USER.COLUMNS.ID))
//                val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER.COLUMNS.NAME))
//                val email = cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER.COLUMNS.EMAIL))
//
//                //Preencher entidade de user
//                fornadaEntity = FornadaEntity(userId, name, email)
//            }
//            cursor.close()
//        } catch (e: Exception) {
//            return fornadaEntity
//        }
//        return fornadaEntity
//    }

    fun getList(): MutableList<FornadaEntity>{
        val list = mutableListOf<FornadaEntity>()
        try{
            val cursor : Cursor
            val db = mFPadariaDatabaseHelper.readableDatabase

            cursor = db.rawQuery("SELECT* FROM ${DataBaseConstants.FORNADA.TABLE_NAME}", null)
            if(cursor.count>0){
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.FORNADA.COLUMNS.ID))
                    val description = cursor.getString(cursor.getColumnIndex(DataBaseConstants.FORNADA.COLUMNS.DESCRIPTION))
                    val dateFinish = cursor.getString(cursor.getColumnIndex(DataBaseConstants.FORNADA.COLUMNS.DATEFINISH))

                    list.add(FornadaEntity(id, description, dateFinish))

                }
            }
        }catch(e: Exception){
            return list
        }
        return list
    }
}