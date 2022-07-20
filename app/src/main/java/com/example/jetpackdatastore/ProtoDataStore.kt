package com.example.jetpackdatastore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException
import com.example.jetpackdatastore.Person as Person1

class ProtoDataStore(private val context: Context) {
    /**
     *  Creating an instance of Datastore<object of proto> by using the  property delegate
     *  name parameter is the name of the Preferences DataStore.
     */
    val Context.dataStore: DataStore<Person1> by dataStore(
        fileName = "my_data",
        serializer = ProtoSerializer()
    )
    // UserPreferences data
    val readData: Flow<com.example.jetpackdatastore.Person> = context.dataStore.data.catch {
        exception ->
        if (exception is IOException){
            Log.d("Error",exception.message.toString())
            emit(com.example.jetpackdatastore.Person.getDefaultInstance()) // Emits the result of the request to the flow
        } else {
            throw exception
        }
    }
    //  for writing data we use DataStore<UserPreferences>.updateData
    suspend fun updateValue(firstName: String){
        context.dataStore.updateData {
            it.toBuilder().setMsg(firstName).build()
        }
    }
}