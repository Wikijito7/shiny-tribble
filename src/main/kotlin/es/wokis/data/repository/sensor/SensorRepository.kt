package es.wokis.data.repository.sensor

import es.wokis.data.bo.sensor.SensorBO
import es.wokis.data.bo.user.UserBO
import es.wokis.data.datasource.local.sensor.SensorLocalDataSource
import es.wokis.data.dto.sensor.SensorsDataDTO
import es.wokis.data.dto.sensor.SimpleSensorsDataDTO
import es.wokis.data.mapper.sensor.toDTO
import es.wokis.data.mapper.sensor.toSimpleDTO

interface SensorRepository {
    suspend fun addData(user: UserBO, data: SensorBO)
    suspend fun getLastSensorData(user: UserBO): SimpleSensorsDataDTO
    suspend fun getAllSensorData(user: UserBO): SensorsDataDTO
    suspend fun getHistoricSensorData(user: UserBO, time: String, interval: String): SensorsDataDTO
}

class SensorRepositoryImpl(
    private val sensorLocalDataSource: SensorLocalDataSource
) : SensorRepository {
    override suspend fun addData(user: UserBO, data: SensorBO) {
        sensorLocalDataSource.addSensorData(user = user, sensor = data)
    }

    override suspend fun getLastSensorData(user: UserBO): SimpleSensorsDataDTO =
        sensorLocalDataSource.getLastSensorData(user = user).toSimpleDTO()

    override suspend fun getAllSensorData(user: UserBO): SensorsDataDTO =
        sensorLocalDataSource.getAllSensorData(user = user).toDTO()

    override suspend fun getHistoricSensorData(
        user: UserBO,
        time: String,
        interval: String
    ): SensorsDataDTO =
        sensorLocalDataSource.getHistoricSensorData(user = user, time = time, interval = interval).toDTO()

}