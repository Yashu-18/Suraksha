package com.example.suraksha.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactsDao {

    @Query("SELECT * FROM contacts_table")
    fun getData(): Flow<List<Contacts>>

    @Query("SELECT phNum FROM contacts_table")
    suspend fun getphNum(): List<String>

    @Query("SELECT COUNT(phNum) FROM contacts_table")
    suspend fun getContactsCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contacts: Contacts)

    @Update
    suspend fun update(contacts: Contacts)

    @Delete
    suspend fun delete(contacts: Contacts)
}