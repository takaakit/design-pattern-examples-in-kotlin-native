package creationalPatterns.builder

import kotlin.system.exitProcess

/*
Create documents in HTML format and text format. It is possible to create different documents
in the same construction process.
 */

fun main() {
    println("Please enter \"plain\" or \"html\":")
    val inputValue = readLine()

    if (inputValue == "plain") {
        val plainTextBuilder = PlainTextBuilder()
        val director = Director(plainTextBuilder)
        director.build()
        val content = plainTextBuilder.content
        println(content)
    } else if (inputValue == "html") {
        val htmlBuilder = HTMLBuilder()
        val director = Director(htmlBuilder)
        director.build()
        val fileName = htmlBuilder.fileName
        println("$fileName has been created.")
        println("Output File: $fileName")
    } else {
        println("The value is not \"plain\" or \"html\".")
        exitProcess(-1)
    }
}
