package behavioralPatterns.command
// ˅
import gtk.GDK_BUTTON_PRIMARY
import org.gtk.dsl.gio.onCreateUI
import org.gtk.dsl.gtk.*
import org.gtk.gtk.common.enums.Orientation
import org.gtk.gtk.controller.gesture.single.GestureDrag
import org.gtk.gtk.widgets.DrawingArea
import org.gtk.gtk.widgets.button.Button

// ˄

class AppMain {
    // ˅
    
    // ˄

    // Painting history
    private val history: HistoryCommand = HistoryCommand()
        // ˅
        
        // ˄

    private lateinit var canvas: PaintingCanvas
        // ˅
        
        // ˄

    private fun onDragged(x: Double, y: Double) {
        // ˅
        val paintingCommand = PaintingCommand(canvas, x, y)
        history.add(paintingCommand)
        paintingCommand.execute()
        // ˄
    }

    private fun undo() {
        // ˅
        canvas.clear()
        history.undo()
        history.execute()
        // ˄
    }

    private fun clear() {
        // ˅
        canvas.clear()
        history.clear()
        // ˄
    }

    // ˅
    init {
        application("behavioralPatterns.command") {
            onCreateUI {
                lateinit var drag: GestureDrag

                applicationWindow {
                    title = "Command Example"
                    sizeRequest = 400 x 350

                    box(Orientation.VERTICAL, 0) {
                        frame {
                            val drawingArea = DrawingArea()
                            drawingArea.sizeRequest = 400 x 300
                            child = drawingArea

                            canvas = PaintingCanvas(drawingArea)

                            drag = GestureDrag()
                            drag.button = GDK_BUTTON_PRIMARY.toUInt()
                            drawingArea.addController(drag)

                            var startX = 0.0
                            var startY = 0.0

                            // Drug start event
                            drag.addOnDragBeginCallback { x, y ->
                                startX = x
                                startY = y

                                onDragged(startX, startY)
                            }

                            // Drag update event
                            drag.addOnDragUpdateCallback { offsetX, offsetY ->
                                onDragged(startX + offsetX, startY + offsetY)
                            }

                            // Drug end event
                            drag.addOnDragEndCallback { offsetX, offsetY ->
                                onDragged(startX + offsetX, startY + offsetY)
                            }
                        }

                        box(Orientation.HORIZONTAL, 4) {
                            isHomogeneous = true

                            Button("Undo").apply {
                                addOnClickedCallback {
                                    undo()
                                }
                            }.also {
                                append(it)
                            }

                            Button("Clear").apply {
                                addOnClickedCallback {
                                    clear()
                                }
                            }.also {
                                append(it)
                            }
                        }
                    }
                }.show()
            }
        }
    }
    // ˄
}

// ˅

// ˄
