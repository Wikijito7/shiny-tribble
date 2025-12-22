package es.wokis.data.datasource.local.sensor

import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoCollection
import es.wokis.data.bo.sensor.SensorBO
import es.wokis.data.bo.sensor.SensorsDataBO
import es.wokis.data.bo.user.UserBO
import es.wokis.data.dbo.sensor.SensorDBO
import es.wokis.data.mapper.sensor.toBOList
import es.wokis.data.mapper.sensor.toDBO
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList

interface SensorLocalDataSource {
    suspend fun addSensorData(user: UserBO, sensor: SensorBO): Boolean
    suspend fun getLastSensorData(user: UserBO): SensorsDataBO
    suspend fun getAllSensorData(user: UserBO): SensorsDataBO
    suspend fun getHistoricSensorData(user: UserBO, time: String, interval: String): SensorsDataBO
}

class SensorLocalDataSourceImpl(
    private val sensorCollection: MongoCollection<SensorDBO>
) : SensorLocalDataSource {

    override suspend fun addSensorData(user: UserBO, sensor: SensorBO): Boolean {
        val filter = Filters.eq(SensorDBO::name.name, sensor.name)
        val sensorDBO = sensorCollection.find<SensorDBO>(filter = filter).firstOrNull()?.let {
            it.copy(
                data = it.data.toMutableList().apply {
                    sensor.data.firstOrNull()?.toDBO()?.let { element -> add(element) }
                }.toList()
            )
        } ?: sensor.toDBO(user.id ?: return false)

        return sensorCollection.replaceOne(filter, sensorDBO).wasAcknowledged()
    }

    override suspend fun getLastSensorData(user: UserBO): SensorsDataBO {
        val filter = Filters.eq(SensorDBO::userId.name, user.id)
        return sensorCollection
            .find<SensorDBO>(filter = filter)
            .toList()
            .map { it.copy(data = listOfNotNull(it.data.lastOrNull())) }
            .toBOList()
            .let {
                SensorsDataBO(sensors = it)
            }
    }

    override suspend  fun getAllSensorData(user: UserBO): SensorsDataBO {
        val filter = Filters.eq(SensorDBO::userId.name, user.id)
        return sensorCollection
            .find<SensorDBO>(filter = filter)
            .toList()
            .toBOList()
            .let {
                SensorsDataBO(sensors = it)
            }
    }

    override suspend  fun getHistoricSensorData(
        user: UserBO,
        time: String,
        interval: String
    ): SensorsDataBO {
        TODO("Not yet implemented")
    }

}