package mx.itson.cheems.persistence

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.util.Log

class CheemsDB(
    context: Context,
    name: String,
    factory: CursorFactory?,
    version: Int
) :
    SQLiteOpenHelper(context, name, factory, version){

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
       try {
           sqLiteDatabase.execSQL("Create Table Winner" (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, nickname Text))
       } catch (ex: Exception){
           Log.e("Error al crear la base de datos", ex.message.toString())
       }
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        TODO("Not yet implemented")
    }
}