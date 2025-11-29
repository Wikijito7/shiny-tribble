package es.wokis.routing

import io.ktor.server.auth.authenticate
import io.ktor.server.plugins.ratelimit.RateLimitName
import io.ktor.server.plugins.ratelimit.rateLimit
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Routing.setUpSensorRouting() {
    rateLimit(RateLimitName("sensor")) {
        authenticate {
            route("/sensor") {
                get {

                }

                post {

                }

                get("/last") {

                }

                get("/historical/{time}/{interval}") {
                    // TODO
                }
            }
        }
    }
}