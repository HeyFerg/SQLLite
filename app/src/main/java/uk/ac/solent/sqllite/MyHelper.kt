package uk.ac.solent.sqllite

import android.database.Cursor
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


data class Song(val id: String, val t: String, val a: String, val y: Long)

class MyHelper(ctx: Context) : SQLiteOpenHelper(ctx, "MusicDB", null, 1){

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("CREATE TABLE IF NOT EXISTS Hits (Id INT PRIMARY KEY, Title VARCHAR(255),"+ "Artist VARCHAR(255), Year INT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion:Int, newVersion:Int) {
        db.execSQL("DROP TABLE IF EXISTS Hits")
        onCreate(db)
    }

    fun addSong(title:String, artist:String, year:Long) : Long{

        val db = writableDatabase
        val stmt = db.compileStatement ("INSERT INTO Hits(Title, Artist, Year) VALUES (?,?,?)");
        stmt.bindString(1, title)
        stmt.bindString(2, artist)
        stmt.bindLong(3, year)
        val id = stmt.executeInsert()
        return id
    }


    fun searchOneSong(id: String) : Song? {

        val db = readableDatabase
        val cursor = db.rawQuery ("SELECT * FROM Hits WHERE ID=?", arrayOf<String>("$id"))

        if(cursor.moveToFirst()){

            val s = Song(cursor.getString(cursor.getColumnIndex("ID:")),
            cursor.getString(cursor.getColumnIndex("Artist:")), cursor.getString(cursor.getColumnIndex("Title:")),
            cursor.getLong(cursor.getColumnIndex("Year:")))
            cursor.close()
            return s
        }
        cursor.close()
        return null
    }

    fun updateRecord(id: String, Title: String, Artist: String, Year: Long): Int{

        val db = writableDatabase
        val stmt = db.compileStatement("UPDATE Hits SET Title=?, Artist=?, Year=?")
        stmt.bindString(1, Title)
        stmt.bindString(2, Artist)
        stmt.bindLong(3, Year)
        val nAffectedRows = stmt.executeUpdateDelete()
        return nAffectedRows
    }

    fun deleteRecord(Title: String, Artist: String, Year: Long): Int{

        val db = writableDatabase
        val stmt = db.compileStatement("DELETE FROM Hits WHERE Title=? AND Artist=? AND Year=?")
        stmt.bindString(1, Title)
        stmt.bindString(2, Artist)
        stmt.bindLong(3, Year)
        val nAffectedRows = stmt.executeUpdateDelete()
        return nAffectedRows

    }
}