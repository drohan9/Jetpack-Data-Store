package com.example.jetpackdatastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class PrefsDataStore(private val context: Context) {

    /**
     *  Creating an instance of Datastore<Preferences> by using the  property delegate
     *  name parameter is the name of the Preferences DataStore.
     */
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    /**
     * This function used to fetch data which is stored
     * To define a key for an int value, use intPreferencesKey() & for string stringPreferencesKey() it converts our key to string prefs key
     */
    suspend fun getD(key: String): String {
        val dataStoreKey = stringPreferencesKey(key)
        val preferences = context.dataStore.data.first() // We DataStore.data property to access the appropriate stored value
        return preferences[dataStoreKey].toString()
    }
//    val getData: Flow<String> = context.dataStore.data
//        .map { preferences ->
//            // No type safety.
//            preferences[stringPreferencesKey("temps")] ?: ""
//        } as Flow<String>

    /**
     * Preferences DataStore provides an edit() function that transactionally updates the data in a DataStore
     */
    suspend fun storeData(key: String,value: String) {
        context.dataStore.edit {
            it[stringPreferencesKey(key)] = value
        }
    }
}