package structuralPatterns.facade
// ˅
import okio.FileSystem
import okio.Path.Companion.toPath
import okio.buffer
import okio.use

// ˄

class HtmlWriter(fileName: String) {
    // ˅
    
    // ˄

    private val fileName: String = fileName
        // ˅
        
        // ˄

    private val builder: StringBuilder = StringBuilder()
        // ˅
        
        // ˄

    // Write a title
    fun heading(title: String) {
        // ˅
        builder.appendLine("<html>")
        builder.appendLine("<head><title>$title</title></head>")
        builder.appendLine("<body>\n")
        builder.appendLine("<h1>$title</h1>\n")
        // ˄
    }

    // Write a paragraph
    fun paragraph(message: String) {
        // ˅
        builder.appendLine("<p>$message</p>\n")
        // ˄
    }

    // Write a mail address
    fun mailto(mailAddress: String, userName: String) {
        // ˅
        anchor("mailto:$mailAddress", userName)
        // ˄
    }

    fun close() {
        // ˅
        builder.appendLine("</body>")
        builder.appendLine("</html>\n")
        FileSystem.SYSTEM.sink(fileName.toPath()).buffer().use { sink -> sink.writeUtf8(builder.toString()) }
        // ˄
    }

    // Write a link
    private fun anchor(url: String, text: String) {
        // ˅
        paragraph("<a href=\"$url\">$text</a>")
        // ˄
    }

    // ˅
    
    // ˄
}

// ˅

// ˄
