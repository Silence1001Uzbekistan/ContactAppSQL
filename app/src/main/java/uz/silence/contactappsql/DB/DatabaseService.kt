package uz.silence.contactappsql.DB

import uz.silence.contactappsql.CLASS.Contact

interface DatabaseService {

    fun addContact(contact: Contact)
    fun getAllContacts(): ArrayList<Contact>
    fun getContactById(id: Int): Contact

}