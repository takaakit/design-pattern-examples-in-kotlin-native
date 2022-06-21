package behavioralPatterns.mediator
// ˅
import org.gtk.gtk.widgets.button.Button

// ˄

class ColleagueButton(button: Button) : Colleague() {
    // ˅
    // ˄

    val isPressed: Boolean
        // ˅
        get() = button.isFocus
        // ˄

    val button: Button = button
        // ˅
        
        // ˄

    // Set enable/disable from the Mediator
    override fun setActivation(isEnable: Boolean) {
        // ˅
        this.button.visible = isEnable
        // ˄
    }

    // ˅
    init {
        button.addOnClickedCallback {
            mediator?.colleagueChanged()
        }
    }
    // ˄
}

// ˅

// ˄
