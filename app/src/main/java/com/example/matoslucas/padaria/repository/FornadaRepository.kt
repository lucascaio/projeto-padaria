package com.example.matoslucas.padaria.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.matoslucas.padaria.constants.DataBaseConstants
import com.example.matoslucas.padaria.entities.FornadaEntity

class FornadaRepository private constructor(context: Context) {
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

    fun get(): FornadaEntity? {
        var fornadaEntity: FornadaEntity? = null
        try {
            val cursor: Cursor
            val db = mFPadariaDatabaseHelper.readableDatabase

            val projection = arrayOf(DataBaseConstants.FORNADA.COLUMNS.ID,
                    DataBaseConstants.FORNADA.COLUMNS.DESCRIPTION,
                    DataBaseConstants.FORNADA.COLUMNS.DATEFINISH)

//            val selection = "${DataBaseConstants.FORNADA.COLUMNS.ID} = ? "
//            val selectionArgs = arrayOf(id)
            cursor = db.query(DataBaseConstants.FORNADA.TABLE_NAME, projection, null, null, null, null, null)
            if (cursor.count > 0) {
                cursor.moveToFirst()
//                val userId = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.USER.COLUMNS.ID))
                val fornadaId = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.FORNADA.COLUMNS.ID))
                val description = cursor.getString(cursor.getColumnIndex(DataBaseConstants.FORNADA.COLUMNS.DESCRIPTION))
                val dateFinish = cursor.getString(cursor.getColumnIndex(DataBaseConstants.FORNADA.COLUMNS.DATEFINISH))

                //Preencher entidade de fornada
                fornadaEntity = FornadaEntity(fornadaId, description, dateFinish)
            }
            cursor.close()
        } catch (e: Exception) {
            return fornadaEntity
        }
        return fornadaEntity
    }

    fun getList(): MutableList<FornadaEntity> {
        val list = mutableListOf<FornadaEntity>()
        try {
            val cursor: Cursor
            val db = mFPadariaDatabaseHelper.readableDatabase

            cursor = db.rawQuery("SELECT* FROM ${DataBaseConstants.FORNADA.TABLE_NAME}", null)
            if (cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.FORNADA.COLUMNS.ID))
                    val description = cursor.getString(cursor.getColumnIndex(DataBaseConstants.FORNADA.COLUMNS.DESCRIPTION))
                    val dateFinish = cursor.getString(cursor.getColumnIndex(DataBaseConstants.FORNADA.COLUMNS.DATEFINISH))

                    list.add(FornadaEntity(id, description, dateFinish))

                }
            }
        } catch (e: Exception) {
            return list
        }
        return list
    }

    //    fun insert(description: String, dateFinish: String): Int {
    fun insert(fornada: FornadaEntity) {
        //TODO: inserir o padeiro
        val db = mFPadariaDatabaseHelper.writableDatabase
        val insertValues = ContentValues()
        insertValues.put(DataBaseConstants.FORNADA.COLUMNS.DESCRIPTION, fornada.description)
        insertValues.put(DataBaseConstants.FORNADA.COLUMNS.DATEFINISH, fornada.dateFinish)

        db.insert(DataBaseConstants.FORNADA.TABLE_NAME, null, insertValues)
    }
}