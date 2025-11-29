package es.wokis.data.bo.sensor

data class SensorsDataBO(
    val sensors: List<SensorDataBO>
)

data class SensorDataBO(
    val name: String,
    val timestamp: Long,
    val temp: Float?,
    val hum: Float?,
    val error: String?,
    val battery: SensorBatteryBO?
)

data class SensorBatteryBO(
    val isCharging: Boolean,
    val percentage: Int
)
