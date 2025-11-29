package es.wokis.data.mapper.sensor

import es.wokis.data.bo.sensor.SensorBatteryBO
import es.wokis.data.bo.sensor.SensorDataBO
import es.wokis.data.bo.sensor.SensorsDataBO
import es.wokis.data.dbo.sensor.SensorBatteryDBO
import es.wokis.data.dbo.sensor.SensorDataDBO
import es.wokis.data.dbo.sensor.SensorsDataDBO
import es.wokis.data.dto.sensor.SensorBatteryDTO
import es.wokis.data.dto.sensor.SensorDataDTO
import es.wokis.data.dto.sensor.SensorsDataDTO

fun SensorsDataDTO.toBO() = SensorsDataBO(
    sensors = sensors.map { it.toBO() }
)

fun SensorDataDTO.toBO() = SensorDataBO(
    name = name,
    timestamp = timestamp ?: System.currentTimeMillis(),
    temp = temp,
    hum = hum,
    error = error,
    battery = battery?.toBO()
)

fun SensorBatteryDTO.toBO() = SensorBatteryBO(
    isCharging = isCharging,
    percentage = percentage
)

fun SensorsDataBO.toDTO() = SensorsDataDTO(
    sensors = sensors.map { it.toDTO() }
)

fun SensorDataBO.toDTO() = SensorDataDTO(
    name = name,
    timestamp = timestamp,
    temp = temp,
    hum = hum,
    error = error,
    battery = battery?.toDTO()
)

fun SensorBatteryBO.toDTO() = SensorBatteryDTO(
    isCharging = isCharging,
    percentage = percentage
)

fun SensorsDataBO.toDBO() = SensorsDataDBO(
    sensors = sensors.map { it.toDBO() }
)

fun SensorDataBO.toDBO() = SensorDataDBO(
    name = name,
    timestamp = timestamp,
    temp = temp,
    hum = hum,
    error = error,
    battery = battery?.toDBO()
)

fun SensorBatteryBO.toDBO() = SensorBatteryDBO(
    isCharging = isCharging,
    percentage = percentage
)

fun SensorsDataDBO.toBO() = SensorsDataBO(
    sensors = sensors.map { it.toBO() }
)

fun SensorDataDBO.toBO() = SensorDataBO(
    name = name,
    timestamp = timestamp,
    temp = temp,
    hum = hum,
    error = error,
    battery = battery?.toBO()
)

fun SensorBatteryDBO.toBO() = SensorBatteryBO(
    isCharging = isCharging,
    percentage = percentage
)

