package structuralPatterns.facade
// ˅
import okio.FileSystem
import okio.Path.Companion.toPath

// ˄

object DataLibrary {
    // ˅
    
    // ˄

    // Read a data library file.
    fun getProperties(dataLibraryFileName: String): MutableMap<String, String> {
        // ˅
        val prop: MutableMap<String, String> = mutableMapOf()
        FileSystem.SYSTEM.read(dataLibraryFileName.toPath()) {
            while (true) {
                val text = readUtf8Line() ?: break
                val (key, value) = text.split("=")
                prop[key] = value
            }
        }
        return prop
        // ˄
    }

    // ˅
    
    // ˄
}

// ˅

// ˄
