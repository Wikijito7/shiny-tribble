package es.wokis.utils

import io.ktor.http.content.*
import io.ktor.http.content.forEachPart
import kotlin.apply
import kotlin.collections.toList

suspend fun MultiPartData.getAllParts(): List<PartData> = mutableListOf<PartData>().apply {
    forEachPart {
        add(it)
    }
}.toList()
