package es.wokis.data.dbo.verification

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class VerificationDBO(
    @SerialName("_id")
    @Contextual val id: ObjectId? = null,
    val email: String,
    val verificationToken: String,
    val timeStamp: Long,
)
