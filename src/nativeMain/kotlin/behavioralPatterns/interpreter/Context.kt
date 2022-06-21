package behavioralPatterns.interpreter
// ˅

// ˄

// Hold data which will be interpreted.
class Context(line: String) {
    // ˅
    // ˄

    private val iter: Iterator<String> = line.split(" ").iterator()
        // ˅
        
        // ˄

    var currentToken: String? = null
        // ˅
        private set
        // ˄

    val number: Int
        // ˅
        get() {
            val number: Int = try {
                currentToken!!.toInt()
            } catch (e: NumberFormatException) {
                throw Exception("WARNING: $e")
            }
            return number
        }
        // ˄

    fun nextToken(): String? {
        // ˅
        if (iter.hasNext()) {
            currentToken = iter.next()
        } else {
            currentToken = null
        }
        return currentToken
        // ˄
    }

    fun slideToken(token: String?) {
        // ˅
        if (token != this.currentToken) {
            throw Exception("WARNING: " + token + " is expected but " + this.currentToken + " was found.")
        }
        nextToken()
        // ˄
    }

    // ˅
    init {
        nextToken()
    }
    // ˄
}

// ˅

// ˄
