package uz.silence.contactappsql.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import uz.silence.contactappsql.CLASS.Contact
import uz.silence.contactappsql.CONSTANTS.Constant

class MyDbHelper(context: Context) :
    SQLiteOpenHelper(context, Constant.DB_NAME, null, Constant.DB_VERSION), DatabaseService {
    override fun onCreate(p0: SQLiteDatabase?) {

        val query =
            "create table ${Constant.TABLE_NAME} (${Constant.ID} integer not null primary key autoincrement unique,${Constant.NAME} text not null,${Constant.PHONE_NUMBER} text not null)"
        p0?.execSQL(query)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("drop table if exists ${Constant.TABLE_NAME}")
        onCreate(p0)
    }

    override fun addContact(contact: Contact) {

        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Constant.NAME, contact.name)
        contentValues.put(Constant.PHONE_NUMBER, contact.number)
        database.insert(Constant.TABLE_NAME, null, contentValues)
        database.close()

    }

    override fun getAllContacts(): ArrayList<Contact> {

        val list = ArrayList<Contact>()
        val query = "select * from ${Constant.TABLE_NAME}"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {

            do {

                val contact = Contact(cursor.getInt(0), cursor.getString(1), cursor.getString(2))
                list.add(contact)

            } while (cursor.moveToNext())

        }

        return list

    }

    override fun getContactById(id: Int): Contact {

        val database = this.readableDatabase
        val cursor = database.query(
            Constant.TABLE_NAME,
            arrayOf(Constant.ID, Constant.NAME, Constant.PHONE_NUMBER), "${Constant.ID} = ?",
            arrayOf(id.toString()),
            null, null, null
        )

        cursor?.moveToFirst()

        return Contact(cursor.getInt(0), cursor.getString(1), cursor.getString(2))

    }
}