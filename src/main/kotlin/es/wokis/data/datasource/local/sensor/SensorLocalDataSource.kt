package es.wokis.data.datasource.local.sensor

import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoCollection
import es.wokis.data.bo.sensor.SensorBO
import es.wokis.data.bo.sensor.SensorsDataBO
import es.wokis.data.bo.user.UserBO
import es.wokis.data.dbo.sensor.SensorDBO
import es.wokis.data.mapper.sensor.toDBO
import kotlinx.coroutines.flow.firstOrNull

interface SensorLocalDataSource {
    suspend fun addSensorData(user: UserBO, sensor: SensorBO): Boolean
    fun getLastSensorData(user: UserBO): SensorsDataBO
    fun getAllSensorData(user: UserBO): SensorsDataBO
    fun getHistoricSensorData(user: UserBO, time: String, interval: String): SensorsDataBO
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

    override fun getLastSensorData(user: UserBO): SensorsDataBO {
        TODO("Not yet implemented")
    }

    override fun getAllSensorData(user: UserBO): SensorsDataBO {
        TODO("Not yet implemented")
    }

    override fun getHistoricSensorData(
        user: UserBO,
        time: String,
        interval: String
    ): SensorsDataBO {
        TODO("Not yet implemented")
    }

}