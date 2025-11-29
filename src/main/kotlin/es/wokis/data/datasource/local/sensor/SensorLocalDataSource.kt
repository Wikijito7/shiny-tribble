package es.wokis.data.datasource.local.sensor

import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoCollection
import es.wokis.data.bo.sensor.SensorDataBO
import es.wokis.data.bo.sensor.SensorsDataBO
import es.wokis.data.bo.user.UserBO
import es.wokis.data.dbo.sensor.SensorsDataDBO
import es.wokis.data.dbo.user.UserDBO
import es.wokis.data.mapper.sensor.toDBO
import es.wokis.data.mapper.user.toDBO
import org.bson.types.ObjectId

interface SensorLocalDataSource {
    suspend fun addSensorData(user: UserBO, data: SensorDataBO): Boolean
    fun getLastSensorData(user: UserBO): SensorsDataBO
    fun getAllSensorData(user: UserBO): SensorsDataBO
    fun getHistoricSensorData(user: UserBO, time: String, interval: String): SensorsDataBO
}

class SensorLocalDataSourceImpl(
    private val userCollection: MongoCollection<UserDBO>
) : SensorLocalDataSource {

    override suspend fun addSensorData(user: UserBO, data: SensorDataBO): Boolean {
        val filter = Filters.eq(UserDBO::id.name, ObjectId(user.id))
        val updatedUser = user.toDBO().let { userDBO ->
            val sensors = userDBO.sensors?.let {
                val updatedSensors = it.sensors.toMutableList().apply {
                    add(data.toDBO())
                }.toList()
                it.copy(sensors = updatedSensors)
            } ?: SensorsDataDBO(listOf(data.toDBO()))
            userDBO.copy(sensors = sensors)
        }
        return userCollection.replaceOne(filter, updatedUser).wasAcknowledged()
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