package mx.itson.cheems.entities

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import mx.itson.cheems.persistence.CheemsDB


class Winner {

    var id = 0
    var name : String = ""
    var nickname : String = ""

    constructor(id: Int, name: String, nickname: String){
    this.id = id
    this.name = name
    this.nickname = nickname

    }

    fun save(context: Context, name: String, nickname: String){
    try {
        val cheemsDB = CheemsDB(context, "CheemsDB", null, 1)

        val database : SQLiteDatabase = cheemsDB.writableDatabase

        val values = contentValues()
        values.put("name", name)
        values.put("nickname", nickname)

        database.insert("Winner", null, values)


    } catch (ex: Exception){
        Log.e("Error saving Winner", ex.message.toString())
    }
    }
}
