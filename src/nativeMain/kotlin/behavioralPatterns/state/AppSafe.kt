package behavioralPatterns.state
// ˅
import org.gtk.dsl.gio.onCreateUI
import org.gtk.dsl.gtk.application
import org.gtk.dsl.gtk.applicationWindow
import org.gtk.dsl.gtk.box
import org.gtk.dsl.gtk.x
import org.gtk.gtk.common.enums.Orientation
import org.gtk.gtk.widgets.TextView
import org.gtk.gtk.widgets.button.Button
import org.gtk.gtk.widgets.entry.Entry
import org.gtk.gtk.widgets.scrolledwindow.ScrolledWindow
import platform.posix.sleep
import kotlin.native.concurrent.TransferMode
import kotlin.native.concurrent.Worker

// ˄

// Safe security system that the security status changes with time.
class AppSafe : Context {
    // ˅
    
    // ˄

    // Current time
    private var entryTime: Entry? = null
        // ˅
        
        // ˄

    // Current state
    private var state: State = DaytimeState
        // ˅
        
        // ˄

    // Display of security center
    private lateinit var textViewMessage: TextView
        // ˅
        
        // ˄

    private lateinit var scrolledWindow: ScrolledWindow
        // ˅
        
        // ˄

    // Set time
    override fun setTime(hour: Int) {
        // ˅
        var currentTime = "Current Time : "
        currentTime += if (hour < 10) {
            "0$hour:00"
        } else {
            "$hour:00"
        }

        println(currentTime)
        entryTime?.buffer?.text = currentTime

        state.setTime(this, hour)
        // ˄
    }

    // Change state
    override fun changeState(state: State) {
        // ˅
        println("The state changed from " + this.state + " to " + state + ".")
        this.state = state
        // ˄
    }

    // Call a security guard room
    override fun callSecurityGuardsRoom(msg: String) {
        // ˅
        textViewMessage.buffer?.insertAtCursor("call! $msg\n")
        scrolledWindow.verticalAdjustment!!.value = scrolledWindow.verticalAdjustment!!.upper
        // ˄
    }

    // Record security log
    override fun recordSecurityLog(msg: String) {
        // ˅
        textViewMessage.buffer?.insertAtCursor("record ... $msg\n")
        scrolledWindow.verticalAdjustment!!.value = scrolledWindow.verticalAdjustment!!.upper
        // ˄
    }

    private fun pressedUseButton() {
        // ˅
        state.use(this)
        // ˄
    }

    private fun pressedAlarmButton() {
        // ˅
        state.alarm(this)
        // ˄
    }

    private fun pressedPhoneButton() {
        // ˅
        state.phone(this)
        // ˄
    }

    // ˅
    init {
        val worker = Worker.start()

        worker.execute(TransferMode.SAFE, { this }) {
            while (true) {
                // Advance one hour for every second of real time.
                for (hour in 0..23) {
                    it.setTime(hour) // Set the time
                    sleep(1)
                }
            }
        }

        application("behavioralPatterns.state") {
            onCreateUI {
                applicationWindow {
                    title = "State Example"
                    box(Orientation.VERTICAL, 4) {
                        entryTime = Entry().also {
                            append(it)
                        }

                        scrolledWindow = ScrolledWindow().apply {
                            sizeRequest = 400 x 200
                            textViewMessage = TextView().apply {
                                editable = false
                            }
                        }.also {
                            append(it)
                            it.child = textViewMessage
                        }

                        box(Orientation.HORIZONTAL, 4) {
                            isHomogeneous = true

                            Button("Use").apply {
                                addOnClickedCallback {
                                    pressedUseButton()
                                }
                            }.also {
                                append(it)
                            }

                            Button("Alarm").apply {
                                addOnClickedCallback {
                                    pressedAlarmButton()
                                }
                            }.also {
                                append(it)
                            }

                            Button("Phone").apply {
                                addOnClickedCallback {
                                    pressedPhoneButton()
                                }
                            }.also {
                                append(it)
                            }
                        }
                    }
                }.show()
            }
        }

        worker.requestTermination()
    }
    // ˄
}

// ˅

// ˄
