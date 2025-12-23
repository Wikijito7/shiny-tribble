package es.wokis.data.dbo.recover

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class RecoverDBO(
    @SerialName("_id")
    @Contextual val id: ObjectId? = null,
    val email: String,
    val recoverToken: String,
    val timeStamp: Long,
)