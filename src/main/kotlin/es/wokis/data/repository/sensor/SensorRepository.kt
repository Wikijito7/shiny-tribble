package es.wokis.data.repository.sensor

import es.wokis.data.bo.response.AcknowledgeBO
import es.wokis.data.bo.sensor.SensorBO
import es.wokis.data.bo.sensor.SensorsDataBO
import es.wokis.data.bo.user.UserBO
import es.wokis.data.datasource.local.sensor.SensorLocalDataSource

interface SensorRepository {
    suspend fun addData(user: UserBO, data: SensorBO): AcknowledgeBO
    suspend fun getLastSensorData(user: UserBO): SensorsDataBO
    suspend fun getAllSensorData(user: UserBO): SensorsDataBO
    suspend fun getHistoricSensorData(user: UserBO, time: String, interval: String): SensorsDataBO
    suspend fun getSensorData(user: UserBO, sensorId: String): SensorBO
    suspend fun updateSensorInfo(user: UserBO, sensorId: String, sensor: SensorBO): SensorBO
    suspend fun removeSensor(user: UserBO, sensorId: String): AcknowledgeBO
    suspend fun removeSensorDataLog(user: UserBO, sensorId: String, timestamp: String): AcknowledgeBO
}

class SensorRepositoryImpl(
    private val sensorLocalDataSource: SensorLocalDataSource
) : SensorRepository {
    override suspend fun addData(user: UserBO, data: SensorBO): AcknowledgeBO {
        return AcknowledgeBO(sensorLocalDataSource.addSensorData(user = user, sensor = data))
    }

    override suspend fun getLastSensorData(user: UserBO): SensorsDataBO =
        sensorLocalDataSource.getLastSensorData(user = user)

    override suspend fun getAllSensorData(user: UserBO): SensorsDataBO =
        sensorLocalDataSource.getAllSensorData(user = user)

    override suspend fun getHistoricSensorData(
        user: UserBO,
        time: String,
        interval: String
    ): SensorsDataBO =
        sensorLocalDataSource.getHistoricSensorData(user = user, time = time, interval = interval)

    override suspend fun getSensorData(
        user: UserBO,
        sensorId: String
    ): SensorBO = sensorLocalDataSource.getSensorData(user, sensorId)

    override suspend fun updateSensorInfo(
        user: UserBO,
        sensorId: String,
        sensor: SensorBO
    ): SensorBO = sensorLocalDataSource.updateSensorInfo(user, sensorId, sensor)

    override suspend fun removeSensor(
        user: UserBO,
        sensorId: String
    ): AcknowledgeBO = sensorLocalDataSource.removeSensor(user, sensorId)

    override suspend fun removeSensorDataLog(
        user: UserBO,
        sensorId: String,
        timestamp: String
    ): AcknowledgeBO = sensorLocalDataSource.removeSensorDataLog(user, sensorId, timestamp)
}
