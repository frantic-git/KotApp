package ru.frantic.kotapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper private constructor(context: Context, DB_VERSION: Int): SQLiteOpenHelper(context, "KotDB", null, DB_VERSION) {

    private val tag:String = "frantic_log"

    companion object {
        private var instance : DBHelper? = null

        fun  getInstance(context: Context, DB_VERSION: Int): DBHelper {
            if (instance == null)  // NOT thread safe!
                instance = DBHelper(context, DB_VERSION)

            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        Log.d(tag,"--------------- CREATE DATABASE -------------")

        db?.beginTransaction()

        db?.execSQL("create table emailList ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "email text" + ");")

        val personName = arrayOf("Шепот", "Хромой", "Душелов", "Меняющий", "Костоправ")
        val personPositions = arrayOf("хранитель","взятый", "сержант", "лейтенант", "хирург")

        val cv = ContentValues()

        // создаем таблицу людей
        db?.execSQL("create table person ("
                + "id integer primary key autoincrement,"
                + "name text, position text);")

        for( i in personName.indices) {
            cv.clear()
            cv.put("name", personName[i])
            cv.put("position", personPositions[i])
            db?.insert("person",null,cv)
        }

        db?.setTransactionSuccessful()
        db?.endTransaction()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.d(tag, " --- onUpgrade database from " + oldVersion
                + " to " + newVersion + " version --- ");

        if (oldVersion == 1 && newVersion == 2) {
            val personName = arrayOf("Шепот", "Хромой", "Душелов", "Меняющий", "Костоправ")
            val personPositions = arrayOf("хранитель","взятый", "сержант", "лейтенант", "хирург")

            val cv = ContentValues()

            // создаем таблицу людей
            db?.execSQL("create table person ("
                    + "id integer primary key autoincrement,"
                    + "name text, position text);")

            for( i in personName.indices) {
                cv.clear()
                cv.put("name", personName[i])
                cv.put("position", personPositions[i])
                db?.insert("person",null,cv)
            }
        }
    }
}