package behavioralPatterns.observer
// ˅

// ˄

// Holds a value and notifies observers when the value is set.
class NumberSubject : Subject() {
    // ˅
    // ˄

    var value: Int = 0
        // ˅
        get() = field
        set(value) {
            // Notify observers when the value is set.
            field = value
            notifyObservers()
        }
        // ˄

    // ˅
    
    // ˄
}

// ˅

// ˄
