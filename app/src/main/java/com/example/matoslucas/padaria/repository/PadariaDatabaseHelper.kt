package com.example.matoslucas.padaria.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.matoslucas.padaria.constants.DataBaseConstants

class PadariaDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION: Int = 1
        private val DATABASE_NAME: String = "padaria.db"
    }

    private val createTableUser = """
        CREATE TABLE ${DataBaseConstants.USER.TABLE_NAME}(
        ${DataBaseConstants.USER.COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
        ${DataBaseConstants.USER.COLUMNS.NAME} TEXT,
        ${DataBaseConstants.USER.COLUMNS.EMAIL} TEXT,
        ${DataBaseConstants.USER.COLUMNS.PASSWORD} TEXT
        );
        """

    private val createTableFornada = """
        CREATE TABLE ${DataBaseConstants.FORNADA.TABLE_NAME}(
        ${DataBaseConstants.FORNADA.COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
        ${DataBaseConstants.FORNADA.COLUMNS.DESCRIPTION} TEXT,
        ${DataBaseConstants.FORNADA.COLUMNS.DATEFINISH} TEXT
        );
        """

    private val deleteTableUser = "drop if exists ${DataBaseConstants.USER.TABLE_NAME}"
    private val deleteTableFornada = "drop if exists ${DataBaseConstants.FORNADA.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createTableUser)
        db.execSQL(createTableFornada)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(deleteTableUser)
        db.execSQL(createTableUser)

        db.execSQL(deleteTableFornada)
        db.execSQL(createTableFornada)
    }


}