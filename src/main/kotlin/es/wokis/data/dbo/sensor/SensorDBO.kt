package es.wokis.data.dbo.sensor

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class SensorDBO(
    @SerialName("_id")
    @Contextual val id: ObjectId? = null,
    val name: String,
    val data: List<SensorDataDBO>,
    val userId: String
)

@Serializable
data class SensorDataDBO(
    val temp: Float?,
    val hum: Float?,
    val timestamp: Long,
    val error: String?,
    val battery: SensorBatteryDBO?
)

@Serializable
data class SensorBatteryDBO(
    val isCharging: Boolean,
    val percentage: Int
)
