package creationalPatterns.abstractFactory.factory
// ˅
import okio.FileSystem
import okio.Path.Companion.toPath
import okio.buffer
import okio.use

// ˄

abstract class Page(title: String, author: String) {
    // ˅
    
    // ˄

    protected val title: String = title
        // ˅
        
        // ˄

    protected val author: String = author
        // ˅
        
        // ˄

    protected var contents: MutableList<Item> = mutableListOf()
        // ˅
        
        // ˄

    abstract fun toHTML(): String

    fun add(item: Item) {
        // ˅
        contents.add(item)
        // ˄
    }

    fun output() {
        // ˅
        val fileName = "$title.html"
        FileSystem.SYSTEM.sink(fileName.toPath()).buffer().use { sink -> sink.writeUtf8(toHTML()) }
        println("$fileName has been created.")
        println("Output File: $fileName")
        // ˄
    }

    // ˅
    
    // ˄
}

// ˅

// ˄
