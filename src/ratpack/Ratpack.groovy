import com.hyunlabs.PixelModule
import com.hyunlabs.PixelService
import ratpack.codahale.metrics.CodaHaleMetricsModule

import java.nio.file.Files

import static ratpack.groovy.Groovy.ratpack

ratpack {
    bindings {
        add new CodaHaleMetricsModule().jvmMetrics().jmx().websocket().healthChecks()
        add new PixelModule()
    }

    handlers { PixelService pixelService ->
        def pixel = Files.newInputStream(launchConfig.baseDir.file("public/blank.gif")).bytes

        get {
            response.headers.set("Cache-Control", "no-cache, no-store, must-revalidate")
            response.headers.set("Pragma", "no-cache")
            response.headers.set("Expires", "0")
            response.headers.set("Content-Type", "image/gif")
            response.headers.set("Content-Length", pixel.length)

            fork {
                pixelService.track(request)
            }

            response.send(pixel)
        }
    }

}
