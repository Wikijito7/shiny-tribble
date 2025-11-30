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
    val temp: Float?,
    @SerialName("hum")
    val hum: Float?,
    @SerialName("timestamp")
    val timestamp: Long?,
    @SerialName("error")
    val error: String?,
    @SerialName("battery")
    val battery: SensorBatteryDTO?
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

    @SerialName("timestamp")
    val timestamp: Long?,

    @SerialName("data")
    val data: List<SensorDataDTO>
)

@Serializable
data class SensorDataDTO(
    @SerialName("temp")
    val temp: Float?,

    @SerialName("hum")
    val hum: Float?,

    @SerialName("timestamp")
    val timestamp: Long,

    @SerialName("error")
    val error: String?,

    @SerialName("battery")
    val battery: SensorBatteryDTO?
)

@Serializable
data class SensorBatteryDTO(
    @SerialName("isCharging")
    val isCharging: Boolean,

    @SerialName("percentage")
    val percentage: Int
)

