package com.hyunlabs

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ratpack.http.Request

class PixelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PixelService)

    void track(Request request) {
        println "=== Incoming Request ==="
        def headers = request.headers
        LOGGER.info new Date().format("yyyy-MM-dd HH:mm:ss")
        LOGGER.info "\t${request.method.name} ${request.uri}"
        headers.names.sort().each { LOGGER.info "\t${it}: ${headers.getAll(it)}"}
        println "=" * 24
    }
}
