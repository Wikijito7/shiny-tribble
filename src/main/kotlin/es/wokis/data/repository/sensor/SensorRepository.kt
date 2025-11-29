package es.wokis.data.repository.sensor

import es.wokis.data.bo.sensor.SensorDataBO
import es.wokis.data.bo.user.UserBO

interface SensorRepository {
    fun addData(user: UserBO, data: SensorDataBO)
    fun getLastSensorData(user: UserBO)
    fun getAllSensorData(user: UserBO)
    fun getHistoricSensorData(user: UserBO, time: String, interval: String)
}

class SensorRepositoryImpl() : SensorRepository {
    override fun addData(user: UserBO, data: SensorDataBO) {
        TODO("Not yet implemented")
    }

    override fun getLastSensorData(user: UserBO) {
        TODO("Not yet implemented")
    }

    override fun getAllSensorData(user: UserBO) {
        TODO("Not yet implemented")
    }

    override fun getHistoricSensorData(
        user: UserBO,
        time: String,
        interval: String
    ) {
        TODO("Not yet implemented")
    }

}