package es.wokis.data.dto.sensor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SimpleSensorsDataDTO(
    @SerialName("sensors")
    val sensors: List<SimpleSensorDataDTO>
)

@Serializable
data class SimpleSensorDataDTO(
    @SerialName("name")
    val name: String,
    @SerialName("temp")
    val temp: Float? = null,
    @SerialName("hum")
    val hum: Float? = null,
    @SerialName("timestamp")
    val timestamp: Long? = null,
    @SerialName("error")
    val error: String? = null,
    @SerialName("battery")
    val battery: SensorBatteryDTO? = null
)

@Serializable
data class SensorsDataDTO(
    @SerialName("sensors")
    val sensors: List<SensorDTO>
)

@Serializable
data class SensorDTO(
    @SerialName("name")
    val name: String,

    @SerialName("data")
    val data: List<SensorDataDTO>
)

@Serializable
data class SensorDataDTO(
    @SerialName("temp")
    val temp: Float? = null,

    @SerialName("hum")
    val hum: Float? = null,

    @SerialName("timestamp")
    val timestamp: Long,

    @SerialName("error")
    val error: String? = null,

    @SerialName("battery")
    val battery: SensorBatteryDTO? = null
)

@Serializable
data class SensorBatteryDTO(
    @SerialName("isCharging")
    val isCharging: Boolean,

    @SerialName("percentage")
    val percentage: Int
)

