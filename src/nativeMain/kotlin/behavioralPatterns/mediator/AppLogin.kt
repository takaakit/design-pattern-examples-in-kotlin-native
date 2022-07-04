package behavioralPatterns.mediator
// ˅
import org.gtk.dsl.gio.onCreateUI
import org.gtk.dsl.gio.onStartup
import org.gtk.dsl.gtk.application
import org.gtk.dsl.gtk.applicationWindow
import org.gtk.dsl.gtk.box
import org.gtk.gtk.common.enums.InputPurpose
import org.gtk.gtk.common.enums.Orientation
import org.gtk.gtk.widgets.button.Button
import org.gtk.gtk.widgets.button.toggleable.checkable.CheckButton
import org.gtk.gtk.widgets.entry.Entry
import org.gtk.gtk.widgets.misc.label.Label
import org.gtk.gtk.widgets.windows.ApplicationWindow

// ˄

class AppLogin : Mediator {
    // ˅
    
    // ˄

    private lateinit var checkButtonLogin: ColleagueCheckButton
        // ˅
        
        // ˄

    private lateinit var checkButtonGuest: ColleagueCheckButton
        // ˅
        
        // ˄

    private lateinit var entryUsername: ColleagueEntry
        // ˅
        
        // ˄

    private lateinit var entryPassword: ColleagueEntry
        // ˅
        
        // ˄

    private lateinit var buttonOk: ColleagueButton
        // ˅
        
        // ˄

    private lateinit var buttonCancel: ColleagueButton
        // ˅
        
        // ˄

    private lateinit var applicationWindow: ApplicationWindow
        // ˅
        
        // ˄

    // Change enable/disable of the Colleagues when notified from the Mediators.
    override fun colleagueChanged() {
        // ˅
        if (buttonOk.isPressed || buttonCancel.isPressed) {
            applicationWindow.destroy()
        } else {
            if (checkButtonGuest.isSelected) {    // Guest mode
                entryUsername.setActivation(false)
                entryPassword.setActivation(false)
                buttonOk.setActivation(true)
            } else {                                  // Login mode
                entryUsername.setActivation(true)
                entryPassword.setActivation(true)

                // Judge whether the changed Colleague is enabled or disabled
                if (!entryUsername.isEmpty && !entryPassword.isEmpty) {
                    buttonOk.setActivation(true)
                } else {
                    buttonOk.setActivation(false)
                }
            }
        }
        // ˄
    }

    override fun createColleagues() {
        // ˅
        application("behavioralPatterns.mediator") {
            onCreateUI {

                applicationWindow {
                    applicationWindow = this
                    title = "Mediator Example"
                    box(Orientation.VERTICAL, 10) {
                        box(Orientation.HORIZONTAL, 10) {
                            val gtkCheckButtonGuest = CheckButton("Guest").apply {
                                active = true
                            }.also {
                                append(it)
                            }

                            val gtkCheckButtonLogin = CheckButton("Login").apply {
                            }.also {
                                append(it)
                            }

                            checkButtonGuest = ColleagueCheckButton(gtkCheckButtonGuest, gtkCheckButtonLogin)
                            checkButtonLogin = ColleagueCheckButton(gtkCheckButtonLogin, gtkCheckButtonGuest)
                        }

                        box(Orientation.VERTICAL, 10) {
                            box(Orientation.HORIZONTAL, 10) {
                                Label("Username:").apply {
                                    widthChars = 10
                                }.also {
                                    append(it)
                                }

                                Entry().apply {
                                    inputPurpose = InputPurpose.NAME
                                    horizontalExpand = true
                                }.also {
                                    append(it)
                                    entryUsername = ColleagueEntry(it)
                                }
                            }

                            box(Orientation.HORIZONTAL, 10) {
                                Label("Password:").apply {
                                    widthChars = 10
                                }.also {
                                    append(it)
                                }

                                Entry().apply {
                                    inputPurpose = InputPurpose.PASSWORD
                                    horizontalExpand = true
                                    isContentVisible = false
                                }.also {
                                    append(it)
                                    entryPassword = ColleagueEntry(it)
                                }
                            }
                        }

                        box(Orientation.HORIZONTAL, 4) {
                            isHomogeneous = true

                            Button("OK").apply {
                            }.also {
                                append(it)
                                buttonOk = ColleagueButton(it)
                            }

                            Button("Cancel").apply {
                            }.also {
                                append(it)
                                buttonCancel = ColleagueButton(it)
                            }
                        }
                    }
                }

                checkButtonLogin.mediator = this@AppLogin
                checkButtonGuest.mediator = this@AppLogin
                entryUsername.mediator = this@AppLogin
                entryPassword.mediator = this@AppLogin
                buttonOk.mediator = this@AppLogin
                buttonCancel.mediator = this@AppLogin

                colleagueChanged()

                applicationWindow.show()
            }
        }
        // ˄
    }

    // ˅
    init {
        createColleagues()
    }
    // ˄
}

// ˅

// ˄
