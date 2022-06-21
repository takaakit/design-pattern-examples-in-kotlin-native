package structuralPatterns.facade
// ˅

// ˄

object PageCreator {
    // ˅
    
    // ˄

    fun createSimpleHomepage(mailAddress: String, htmlFileName: String) {
        // ˅
        val addressBook = DataLibrary.getProperties("src/nativeMain/kotlin/structuralPatterns/facade/addressbook.txt")
        val userName = addressBook[mailAddress]
        
        val writer = HtmlWriter(htmlFileName)
        writer.heading("$userName's homepage")
        writer.paragraph("Welcome to $userName's homepage.")
        writer.paragraph("Please email me at this address.")
        writer.mailto(mailAddress, userName!!)
        writer.close()
        println("$htmlFileName is created for $mailAddress ($userName)")
        println("Output File: $htmlFileName")
        // ˄
    }

    // ˅
    
    // ˄
}

// ˅

// ˄
