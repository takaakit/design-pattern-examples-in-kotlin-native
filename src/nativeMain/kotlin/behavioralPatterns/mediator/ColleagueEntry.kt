package behavioralPatterns.mediator
// ˅
import org.gtk.gtk.widgets.entry.Entry

// ˄

class ColleagueEntry(entry: Entry) : Colleague() {
    // ˅
    
    // ˄

    val isEmpty: Boolean
        // ˅
        get() = currentBufferLength == 0
        // ˄

    private var currentBufferLength: Int = 0
        // ˅
        
        // ˄

    val entry: Entry = entry
        // ˅
        
        // ˄

    // Set enable/disable from the Mediator
    override fun setActivation(isEnable: Boolean) {
        // ˅
        if (isEnable) {
            entry.opacity = 1.0
            entry.canFocus = true
        } else {
            entry.opacity = 0.0
            entry.canFocus = false
        }
        // ˄
    }

    // ˅
    init {
        entry.buffer.addDeletedTextCallback { _, uInt ->
            currentBufferLength = (this.length - uInt).toInt()
            mediator?.colleagueChanged()
        }

        entry.buffer.addInsertedTextCallback { _, _, _ ->
            currentBufferLength = this.length.toInt()
            mediator?.colleagueChanged()
        }
    }
    // ˄
}

// ˅

// ˄
