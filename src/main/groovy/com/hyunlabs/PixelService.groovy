package com.hyunlabs

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ratpack.http.Request

class PixelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PixelService)

    void track(Request request) {
        def headers = request.headers

        LOGGER.info "\n=== Incoming Request ===\n{}\n{}\n{}\n{}",
            new Date().format("yyyy-MM-dd HH:mm:ss"),
            "\t$request.method.name $request.uri",
            headers.names.sort().collect { "\t${it}: ${headers.getAll(it)}"}.join("\n"),
            "=" * 24
    }
}
