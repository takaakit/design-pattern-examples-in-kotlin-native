package structuralPatterns.flyweight
// ˅
import okio.FileSystem
import okio.Path.Companion.toPath
import platform.posix.opendir
import platform.posix.readdir

// ˄

class LargeSizeChar(charName: Char) {
    // ˅
    // ˄

    // Display data of the large size character
    private lateinit var displayData: String
        // ˅
        
        // ˄

    // Display the large size character
    fun display() {
        // ˅
        println(displayData)
        // ˄
    }

    // ˅
    init {
        try {
            FileSystem.SYSTEM.read("src/nativeMain/kotlin/structuralPatterns/flyweight/big$charName.txt".toPath()) {
                displayData = readUtf8()
            }
        }
        catch (e: Exception) {
            displayData = "$charName?"
        }
    }
    // ˄
}

// ˅

// ˄
