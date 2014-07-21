package com.hyunlabs

import ratpack.http.Request

class PixelService {
    void track(Request request) {
        def message = ["=== Incoming Request ===", new Date().format("yyyy-MM-dd HH:mm:ss")]
        def headers = request.headers
        message << "\t${request.method.name} ${request.uri}"
        headers.names.sort().each { message << "\t${it}: ${headers.getAll(it)}"}
        message << "=" * 24
        println message.join('\n')
    }
}
