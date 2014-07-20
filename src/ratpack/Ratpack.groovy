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
            response.headers.with {
                set("Cache-Control", "no-cache, no-store, must-revalidate")
                set("Pragma", "no-cache")
                set("Expires", "0")
                set("Content-Type", "image/gif")
                set("Content-Length", pixel.length)
            }

            onClose { // do this after sending the data to give the data to client ASAP
                pixelService.track(request)
            }

            response.send(pixel)
        }
    }

}
