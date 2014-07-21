import com.hyunlabs.PixelModule
import com.hyunlabs.PixelService
import ratpack.codahale.metrics.CodaHaleMetricsModule

import static ratpack.groovy.Groovy.ratpack

ratpack {
    bindings {
        add new CodaHaleMetricsModule().jvmMetrics().jmx().websocket().healthChecks()
        add new PixelModule()
    }

    handlers { PixelService pixelService ->
        get {
            response.headers.set("Cache-Control", "no-cache, no-store, must-revalidate")
            response.headers.set("Pragma", "no-cache")
            response.headers.set("Expires", "0")

            blocking {
                pixelService.track(request)
            } then {

            }

            render file('public/blank.gif')
        }
    }

}
