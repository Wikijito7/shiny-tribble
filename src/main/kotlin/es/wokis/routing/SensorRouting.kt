package es.wokis.routing

import es.wokis.data.dto.sensor.SensorDTO
import es.wokis.data.dto.sensor.SimpleSensorDataDTO
import es.wokis.data.mapper.acknowledge.toDTO
import es.wokis.data.mapper.sensor.toBO
import es.wokis.data.mapper.sensor.toDTO
import es.wokis.data.mapper.sensor.toSimpleDTO
import es.wokis.data.repository.sensor.SensorRepository
import es.wokis.utils.user
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.plugins.ratelimit.RateLimitName
import io.ktor.server.plugins.ratelimit.rateLimit
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Routing.setUpSensorRouting() {
    val sensorRepository by inject<SensorRepository>()

    rateLimit(RateLimitName("sensor")) {
        authenticate {
            route("/sensor") {
                get {
                    call.user?.let { user ->
                        sensorRepository.getAllSensorData(user).let {
                            call.respond(status = HttpStatusCode.OK, message = it.toDTO())
                        }
                    } ?: call.respond(HttpStatusCode.BadRequest)
                }

                post {
                    call.user?.let { user ->
                        val data = call.receive<SensorDTO>()
                        sensorRepository.addData(user = user, data = data.toBO()).let {
                            call.respond(status = HttpStatusCode.OK, message = it.toDTO())
                        }
                    } ?: call.respond(HttpStatusCode.BadRequest)
                }

                post("/simple") {
                    call.user?.let { user ->
                        val data = call.receive<SimpleSensorDataDTO>()
                        sensorRepository.addData(user = user, data = data.toBO()).let {
                            call.respond(status = HttpStatusCode.OK, message = it.toDTO())
                        }
                    } ?: call.respond(HttpStatusCode.BadRequest)
                }

                get("/last") {
                    call.user?.let { user ->
                        sensorRepository.getLastSensorData(user).let {
                            call.respond(status = HttpStatusCode.OK, message = it.toSimpleDTO())
                        }
                    } ?: call.respond(HttpStatusCode.BadRequest)
                }

                get("/historical/{time}/{interval}") {
                    call.respond(HttpStatusCode.NotImplemented)
                }

                route("/{id}") {
                    get {
                        val sensorId = call.parameters["id"]
                        call.user?.let { user ->
                            sensorId?.let {
                                try {
                                    sensorRepository.getSensorData(user, sensorId).let {
                                        call.respond(HttpStatusCode.OK, it)
                                    }
                                } catch (exc: Exception) {
                                    call.respond(HttpStatusCode.Forbidden)
                                }
                            } ?: call.respond(HttpStatusCode.BadRequest)
                        } ?: call.respond(HttpStatusCode.BadRequest)
                    }

                    put {
                        val sensorId = call.parameters["id"]
                        call.user?.let { user ->
                            sensorId?.let {
                                val sensor = call.receive<SensorDTO>()
                                sensorRepository.updateSensorInfo(user, sensorId, sensor.toBO()).let {
                                    call.respond(HttpStatusCode.OK, it.toDTO())
                                }
                            } ?: call.respond(HttpStatusCode.BadRequest)
                        } ?: call.respond(HttpStatusCode.BadRequest)
                    }

                    delete {
                        val sensorId = call.parameters["id"]
                        call.user?.let { user ->
                            sensorId?.let {
                                sensorRepository.removeSensor(user, sensorId).let {
                                    call.respond(HttpStatusCode.OK, it.toDTO())
                                }
                            }
                        }
                    }

                    delete("/{timestamp}") {
                        val sensorId = call.parameters["id"]
                        val timestamp = call.parameters["timestamp"]
                        call.user?.let { user ->
                            sensorId?.let {
                                timestamp?.let {
                                    sensorRepository.removeSensorDataLog(user, sensorId, timestamp).let {
                                        call.respond(HttpStatusCode.OK, it.toDTO())
                                    }
                                } ?: call.respond(HttpStatusCode.BadRequest)
                            } ?: call.respond(HttpStatusCode.BadRequest)
                        } ?: call.respond(HttpStatusCode.BadRequest)
                    }
                }
            }
        }
    }
}