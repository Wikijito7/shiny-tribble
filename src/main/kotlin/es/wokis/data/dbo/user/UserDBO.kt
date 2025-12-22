package es.wokis.data.dbo.user

import es.wokis.data.constants.ServerConstants
import es.wokis.data.constants.ServerConstants.DEFAULT_LANG
import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.bson.types.ObjectId
import java.util.Date

@Serializable
data class UserDBO(
    @SerialName("_id")
    @Serializable(ObjectIdSerializer::class)
    @Contextual val id: ObjectId? = null,
    val username: String,
    val email: String,
    val password: String,
    val lang: String = DEFAULT_LANG,
    val image: String = ServerConstants.EMPTY_TEXT,
    val createdOn: Long = Date().time,
    val emailVerified: Boolean = false,
    val totpEncodedSecret: ByteArray? = null,
    val sessions: List<String> = emptyList(),
    val recoverWords: List<String> = emptyList(),
)

object ObjectIdSerializer : KSerializer<ObjectId> {
    override val descriptor =
        PrimitiveSerialDescriptor("ObjectId", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: ObjectId) {
        encoder.encodeString(value.toHexString())
    }

    override fun deserialize(decoder: Decoder): ObjectId {
        return ObjectId(decoder.decodeString())
    }
}
