package com.hyunlabs

import ratpack.http.Request

class PixelService {
    void track(Request request) {
        println "=== Incoming Request ==="
        def headers = request.headers
        println new Date().format("yyyy-MM-dd HH:mm:ss")
        println "\t${request.method.name} ${request.uri}"
        headers.names.sort().each { println "\t${it}: ${headers.getAll(it)}"}
        println "=" * 24
    }
}
