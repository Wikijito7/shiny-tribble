package es.wokis.data.mapper.sensor

import es.wokis.data.bo.sensor.SensorBatteryBO
import es.wokis.data.bo.sensor.SensorBO
import es.wokis.data.bo.sensor.SensorDataBO
import es.wokis.data.bo.sensor.SensorsDataBO
import es.wokis.data.dbo.sensor.SensorBatteryDBO
import es.wokis.data.dbo.sensor.SensorDBO
import es.wokis.data.dbo.sensor.SensorDataDBO
import es.wokis.data.dto.sensor.SensorBatteryDTO
import es.wokis.data.dto.sensor.SensorDTO
import es.wokis.data.dto.sensor.SensorDataDTO
import es.wokis.data.dto.sensor.SensorsDataDTO
import es.wokis.data.dto.sensor.SimpleSensorDataDTO
import es.wokis.data.dto.sensor.SimpleSensorsDataDTO

fun List<SensorDBO>.toBOList() = map { it.toBO() }
fun List<SensorBO>.toDBOList(userId: String) = map { it.toDBO(userId) }

fun SensorsDataBO.toSimpleDTO() = SimpleSensorsDataDTO(
    sensors = sensors.map { it.toSimpleDTO() }
)

fun SensorBO.toSimpleDTO() = SimpleSensorDataDTO(
    name = name,
    temp = data.lastOrNull()?.temp,
    hum = data.lastOrNull()?.hum,
    timestamp = data.lastOrNull()?.timestamp,
    error = data.lastOrNull()?.error,
    battery = data.lastOrNull()?.battery?.toDTO()
)

fun SimpleSensorsDataDTO.toBO() = SensorsDataBO(
    sensors = sensors.map { it.toBO() }
)

fun SimpleSensorDataDTO.toBO() = SensorBO(
    name = name,
    data = listOf(
        SensorDataBO(
            temp = temp,
            hum = hum,
            timestamp = timestamp ?: System.currentTimeMillis(),
            error = error,
            battery = battery?.toBO()
        )
    )
)

fun SensorsDataBO.toDTO() = SensorsDataDTO(
    sensors = sensors.map { it.toDTO() }
)

fun SensorsDataDTO.toBO() = SensorsDataBO(
    sensors = sensors.map { it.toBO() }
)

fun SensorBO.toDTO() = SensorDTO(
    name = name,
    data = data.map { it.toDTO() }
)

fun SensorDTO.toBO() = SensorBO(
    name = name,
    data = data.map { it.toBO() }
)

fun SensorDataBO.toDTO() = SensorDataDTO(
    temp = temp,
    hum = hum,
    timestamp = timestamp,
    error = error,
    battery = battery?.toDTO()
)

fun SensorDataDTO.toBO() = SensorDataBO(
    temp = temp,
    hum = hum,
    timestamp = timestamp,
    error = error,
    battery = battery?.toBO()
)

fun SensorBatteryBO.toDTO() = SensorBatteryDTO(
    isCharging = isCharging,
    percentage = percentage
)

fun SensorBatteryDTO.toBO() = SensorBatteryBO(
    isCharging = isCharging,
    percentage = percentage
)

fun SensorDBO.toBO() = SensorBO(
    name = name,
    data = data.map { it.toBO() }
)

fun SensorBO.toDBO(userId: String) = SensorDBO(
    name = name,
    data = data.map { it.toDBO() },
    userId = userId
)

fun SensorDataDBO.toBO() = SensorDataBO(
    temp = temp,
    hum = hum,
    timestamp = timestamp,
    error = error,
    battery = battery?.toBO()
)

fun SensorDataBO.toDBO() = SensorDataDBO(
    temp = temp,
    hum = hum,
    timestamp = timestamp,
    error = error,
    battery = battery?.toDBO()
)

fun SensorBatteryDBO.toBO() = SensorBatteryBO(
    isCharging = isCharging,
    percentage = percentage
)

fun SensorBatteryBO.toDBO() = SensorBatteryDBO(
    isCharging = isCharging,
    percentage = percentage
)
