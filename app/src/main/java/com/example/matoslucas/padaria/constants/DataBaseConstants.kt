package com.example.matoslucas.padaria.constants

class DataBaseConstants {
    object USER{
        val TABLE_NAME = "user"

        object COLUMNS{
            val ID = "id"
            val NAME = "name"
            val EMAIL = "email"
            val PASSWORD = "password"
        }
    }

    object FORNADA{
        val TABLE_NAME = "fornada"

        object COLUMNS{
            val ID = "id"
            val DESCRIPTION = "description"
            val DATEFINISH = "datefinish"
        }
    }
}