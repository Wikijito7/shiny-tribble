package es.wokis.data.bo.sensor

data class SensorsDataBO(
    val sensors: List<SensorBO>
)

data class SensorBO(
    val name: String,
    val timestamp: Long,
    val data: List<SensorDataBO>
)

data class SensorDataBO(
    val temp: Float?,
    val hum: Float?,
    val timestamp: Long,
    val error: String?,
    val battery: SensorBatteryBO?
)

data class SensorBatteryBO(
    val isCharging: Boolean,
    val percentage: Int
)
