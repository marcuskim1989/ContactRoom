package com.marcuskim.contactroom.data;

import android.app.Application;

import com.marcuskim.contactroom.model.Contact;
import com.marcuskim.contactroom.util.ContactRoomDatabase;


import java.util.List;

import androidx.lifecycle.LiveData;

public class ContactRepository {
    private ContactDao contactDao;
    private LiveData<List<Contact>> allContacts;

    public ContactRepository(Application application) {
        ContactRoomDatabase db = ContactRoomDatabase.getDatabase(application);
        contactDao = db.contactDao();

        allContacts = contactDao.getAllContacts();

    }
    public LiveData<List<Contact>> getAllData() { return allContacts; }
    public void insert(Contact contact) {
        ContactRoomDatabase.databaseWriteExecutor.execute(() -> contactDao.insert(contact));
    }
    public LiveData<Contact> get(int id) {
        return contactDao.get(id);
    }
    public void update(Contact contact) {
        ContactRoomDatabase.databaseWriteExecutor.execute(() -> contactDao.update(contact));
    }
    public void delete(Contact contact) {
        ContactRoomDatabase.databaseWriteExecutor.execute(() -> contactDao.delete(contact));
    }

}
