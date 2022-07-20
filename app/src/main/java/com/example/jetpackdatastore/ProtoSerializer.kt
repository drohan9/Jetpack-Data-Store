package com.example.jetpackdatastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

class ProtoSerializer: Serializer<Person>{
    //useing Kotlin serialization to structure your data

    /// readFrom — inverse from the above, how to transform from a storage format into a corresponding memory representation
    override suspend fun readFrom(input: InputStream): Person {
        try {
           return Person.parseFrom(input)
        }catch (exception: InvalidProtocolBufferException){
            throw CorruptionException("Unable to read protocol buffer",exception)
        }
    }


    // writeTo — how to transform the memory representation of our data object into a format fit for storage
    override suspend fun writeTo(t: Person, output: OutputStream) {
        t.writeTo(output)
    }

    //     defaultValue — what to return if no data is found
    override val defaultValue: Person
        get() = Person.getDefaultInstance()

}