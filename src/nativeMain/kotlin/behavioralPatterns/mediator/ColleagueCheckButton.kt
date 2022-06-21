package behavioralPatterns.mediator
// ˅
import org.gtk.gtk.widgets.button.toggleable.checkable.CheckButton

// ˄

class ColleagueCheckButton(ownCheckButton: CheckButton, otherCheckButton: CheckButton) : Colleague() {
    // ˅
    
    // ˄

    val isSelected: Boolean
        // ˅
        get() = ownCheckButton.active
        // ˄

    val ownCheckButton: CheckButton = ownCheckButton
        // ˅
        
        // ˄

    val otherCheckButton: CheckButton = otherCheckButton
        // ˅
        
        // ˄

    // Set enable/disable from the Mediator
    override fun setActivation(isEnable: Boolean) {
        // ˅
        this.ownCheckButton.active = !isEnable
        // ˄
    }

    // ˅
    init {
        ownCheckButton.addOnToggledCallback {
            otherCheckButton.active = !this.active

            mediator?.colleagueChanged()
        }
    }
    // ˄
}

// ˅

// ˄
