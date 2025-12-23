package es.wokis.data.datasource.local.sensor

import com.mongodb.client.model.Accumulators.last
import com.mongodb.client.model.Aggregates.group
import com.mongodb.client.model.Aggregates.match
import com.mongodb.client.model.Aggregates.sort
import com.mongodb.client.model.Aggregates.unwind
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Indexes.ascending
import com.mongodb.kotlin.client.coroutine.MongoCollection
import es.wokis.data.bo.sensor.SensorBO
import es.wokis.data.bo.sensor.SensorsDataBO
import es.wokis.data.bo.user.UserBO
import es.wokis.data.constants.ServerConstants
import es.wokis.data.dbo.sensor.SensorDBO
import es.wokis.data.mapper.sensor.toBOList
import es.wokis.data.mapper.sensor.toDBO
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.Document

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
        val filter = Filters.and(Filters.eq(SensorDBO::name.name, sensor.name), Filters.eq(SensorDBO::userId.name, user.id))
        val sensorDBO = sensorCollection.find<SensorDBO>(filter = filter).firstOrNull()?.let {
            it.copy(
                data = it.data.toMutableList().apply {
                    sensor.data.firstOrNull()?.toDBO()?.let { element -> add(element) }
                }.toList()
            )
        }

        return sensorDBO?.let {
            sensorCollection.replaceOne(filter, sensorDBO).wasAcknowledged()
        } ?: run {
            sensorCollection.insertOne(sensor.toDBO(user.id ?: return false)).wasAcknowledged()
        }
    }

    override suspend fun getLastSensorData(user: UserBO): SensorsDataBO {
        val filter = Filters.eq(SensorDBO::userId.name, user.id)
        val pipeline = listOf(
            match(filter),

            Document(
                "\$project",
                Document("name", 1)
                    .append("userId", 1)
                    .append(
                        "data",
                        Document(
                            "\$slice",
                            listOf(
                                Document(
                                    "\$sortArray",
                                    Document("input", "\$data")
                                        .append("sortBy", Document("timestamp", 1))
                                ),
                                -1
                            )
                        )
                    )
            )
        )


        return sensorCollection.aggregate(pipeline)
            .toList()
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