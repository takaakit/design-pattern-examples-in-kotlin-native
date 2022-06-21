package creationalPatterns.builder
// ˅
import okio.FileSystem
import okio.Path.Companion.toPath
import okio.buffer
import okio.use

// ˄

class HTMLBuilder : Builder {
    // ˅
    // ˄

    // File name to create
    var fileName: String? = null
        // ˅
        private set
        // ˄

    private val builder: StringBuilder = StringBuilder()
        // ˅
        
        // ˄

    // Make a title of HTML file
    override fun createTitle(title: String) {
        // ˅
        fileName = "$title.html" // Set a title as a file name
        builder.appendLine("<html><head><title>$title</title></head><body>")    // Write a title
        builder.appendLine("<h1>$title</h1>")
        // ˄
    }

    // Make a section of HTML file
    override fun createSection(section: String) {
        // ˅
        builder.appendLine("<p>$section</p>")   // Write a section
        // ˄
    }

    // Make items of HTML file
    override fun createItems(items: Array<String>) {
        // ˅
        builder.appendLine("<ul>")  // Write items
        for (item in items) {
            builder.appendLine("<li>$item</li>")
        }
        builder.appendLine("</ul>")
        // ˄
    }

    override fun close() {
        // ˅
        builder.appendLine("</body></html>")
        FileSystem.SYSTEM.sink(fileName!!.toPath()).buffer().use { sink -> sink.writeUtf8(builder.toString()) }
        // ˄
    }

    // ˅
    
    // ˄
}

// ˅

// ˄
