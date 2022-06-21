package behavioralPatterns.interpreter

import okio.FileSystem
import okio.Path.Companion.toPath

/*
An interpreter for mini language to operate radio controlled car. It parses the following syntax
composed of "forward", "left", "right", and "repeat" commands:
```
<program>      ::= program <command list>
<command list> ::= <command>* end
<command>      ::= <repeat> | <action>
<repeat>       ::= repeat <number> <command list>
<action>       ::= forward | right | left
<number>       ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
```
___
Examples before and after syntax analysis.

Ex.1
```
Before parsing : program end
After parsing  : [program []]
```

Ex.2
```
Before parsing : program forward right left end
After parsing  : [program [forward, right, left]]
```

Ex.3
```
Before parsing : program repeat 4 forward right end end
After parsing  : [program [repeat 4 [forward, right]]]
```
*/

fun main() {
    FileSystem.SYSTEM.read("src/nativeMain/kotlin/behavioralPatterns/interpreter/program.txt".toPath()) {
        while (true) {
            val text = readUtf8Line() ?: break
            println("Before parsing : $text")
            val node = Program()
            node.parse(Context(text))
            println("After parsing  : $node")
        }
    }
}
