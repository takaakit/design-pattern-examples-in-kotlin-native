package behavioralPatterns.command
// ˅
import cairo.CAIRO_CONTENT_COLOR
import gobject.G_CONNECT_AFTER
import org.gtk.cairo.Cairo
import org.gtk.cairo.Surface
import org.gtk.gtk.widgets.DrawingArea

// ˄

class PaintingCanvas(drawingArea: DrawingArea) : PaintingTarget {
    // ˅

    // ˄

    // Size of drawing point
    private val pointSize: Double = 15.0
        // ˅
        
        // ˄

    private val paintingColorRed: Double = 0.5
        // ˅
        
        // ˄

    private val paintingColorGreen: Double = 1.0
        // ˅
        
        // ˄

    private val paintingColorBlue: Double = 0.5
        // ˅
        
        // ˄

    private val drawingArea: DrawingArea = drawingArea
        // ˅
        
        // ˄

    private var surface: Surface? = null
        // ˅
        
        // ˄

    override fun paint(x: Double, y: Double) {
        // ˅
        val cairo = Cairo(surface!!)
        cairo.setSourceRGB(paintingColorRed, paintingColorGreen, paintingColorBlue)
        cairo.rectangle(x - (pointSize * 0.5), y - (pointSize * 0.5), pointSize, pointSize)
        cairo.fill()
        cairo.destroy()

        drawingArea.queueDraw()
        // ˄
    }

    override fun clear() {
        // ˅
        val cairo = Cairo(surface!!)
        cairo.setSourceRGB(1.0, 1.0, 1.0)
        cairo.paint()
        cairo.destroy()

        drawingArea.queueDraw()
        // ˄
    }

    // ˅
    init {
        drawingArea.setOnDrawFunction { cairo, _, _ ->
            cairo.setSourceSurface(surface!!, 0.0, 0.0)
            cairo.paint()
        }

        drawingArea.addOnResizeCallback(flags = G_CONNECT_AFTER) { _, _ ->
            // Create a new Surface object to fit the current drawing area size
            surface?.destroy()
            surface = null

            if (drawingArea.native?.surface != null) {
                surface = drawingArea.native!!.surface.createSimilarSurface(
                        CAIRO_CONTENT_COLOR,
                        drawingArea.width,
                        drawingArea.height
                )

                // Draw the Surface in white
                val cairo = Cairo(surface!!)
                cairo.setSourceRGB(1.0, 1.0, 1.0)
                cairo.paint()
                cairo.destroy()
            }
        }
    }
    // ˄
}

// ˅

// ˄
