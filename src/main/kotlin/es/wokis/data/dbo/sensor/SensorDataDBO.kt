package es.wokis.data.dbo.sensor

data class SensorsDataDBO(
    val sensors: List<SensorDataDBO>
)

data class SensorDataDBO(
    val name: String,
    val temp: Float?,
    val hum: Float?,
    val timestamp: Long,
    val error: String?,
    val battery: SensorBatteryDBO?
)

data class SensorBatteryDBO(
    val isCharging: Boolean,
    val percentage: Int
)
