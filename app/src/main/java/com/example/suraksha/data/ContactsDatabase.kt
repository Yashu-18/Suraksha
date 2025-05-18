package com.example.suraksha.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.suraksha.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Contacts::class], version = 1)
abstract class ContactsDatabase : RoomDatabase() {

    abstract fun contactsDoa(): ContactsDao

    class Callback @Inject constructor(
        private val database: Provider<ContactsDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
        }
    }
}