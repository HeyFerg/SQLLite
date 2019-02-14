package uk.ac.solent.sqllite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyHelper(ctx: Context) : SQLiteOpenHelper(ctx, "MusicDB", null, 1){

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("CREATE TABLE IF NOT EXISTS Hits (Id INT PRIMARY KEY, Title VARCHAR(255),"+ "Artist VARCHAR(255), Year INT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion:Int, newVersion:Int) {
        db.execSQL("DROP TABLE IF EXISTS Hits")
        onCreate(db)
    }

    fun addSong(title:String, artist:String, year:Long){

        val db = writableDatabase
        val stmt = db.compileStatement ("INSERT INTO Hits(Title, Artist, Year) VALUES (?,?,?)");
        stmt.bindString(1, title)
        stmt.bindString(2, artist)
        stmt.bindString(3, year)
        val id = stmt.executeInsert()
        return id
    }
}