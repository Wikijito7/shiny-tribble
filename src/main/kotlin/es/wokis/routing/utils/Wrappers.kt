package es.wokis.routing.utils

import es.wokis.data.bo.user.UserBO
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

suspend inline fun RoutingContext.verified(user: UserBO, block: () -> Unit) {
    val verified = user.emailVerified
    if (verified) {
        block()
        return
    }

    call.respond(HttpStatusCode.ExpectationFailed, "User not verified")
}