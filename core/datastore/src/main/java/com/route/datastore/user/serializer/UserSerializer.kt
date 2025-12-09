package com.route.datastore.user.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.route.model.User
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single
import java.io.InputStream
import java.io.OutputStream

@Single
class UserSerializer : Serializer<User> {
    override val defaultValue: User = User("", "", "", "")

    override suspend fun readFrom(input: InputStream): User {
        return try {
            Json.Default.decodeFromString(
                input.readBytes().decodeToString()
            )
        } catch (serialization: SerializationException) {
            throw CorruptionException("Unable to read User", serialization)
        }
    }

    override suspend fun writeTo(t: User, output: OutputStream) {
        output.write(
            Json.encodeToString(t)
                .encodeToByteArray()
        )
    }
}
