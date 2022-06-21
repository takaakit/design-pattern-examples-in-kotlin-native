package behavioralPatterns.command
// ˅

// ˄

// Holder of the past commands
class HistoryCommand : Command {
    // ˅
    
    // ˄

    // A set of past commands
    private val pastCommands: MutableList<Command> = mutableListOf()
        // ˅
        
        // ˄

    override fun execute() {
        // ˅
        for (command: Command in pastCommands) {
            command.execute()
        }
        // ˄
    }

    fun add(cmd: Command) {
        // ˅
        pastCommands.add(cmd)
        // ˄
    }

    // Delete the last command
    fun undo() {
        // ˅
        if (pastCommands.isNotEmpty()) {
            pastCommands.removeLast()
        }
        // ˄
    }

    // Delete all past commands.
    fun clear() {
        // ˅
        pastCommands.clear()
        // ˄
    }

    // ˅

    // ˄
}

// ˅

// ˄
