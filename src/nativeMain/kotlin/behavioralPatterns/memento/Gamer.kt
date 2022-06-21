package behavioralPatterns.memento
// ˅
import kotlin.system.exitProcess
import kotlin.random.Random

// ˄

class Gamer(money: Int) {
    // ˅
    
    // ˄

    // Gamer's money
    var money: Int = money
        // ˅
        
        // ˄

    fun createMemento(): Memento {
        // ˅
        return Memento(money)
        // ˄
    }

    fun setMemento(memento: Memento) {
        // ˅
        money = memento.money
        // ˄
    }

    // Play a game
    fun play() {
        // ˅
        val dice = Random.nextInt(6) + 1 // Shake a dice
        println("The number of dice is $dice.")
        val preMoney = money
        when (dice) {
            1, 3, 5 -> {
                // In case of odd...Money is halved
                money /= 2
                println("Gamer's money is halved: $preMoney -> $money")
            }
            2, 4, 6 -> {
                // In case of even...Money doubles
                money *= 2
                println("Gamer's money doubles: $preMoney -> $money")
            }
            else -> {
                // Other...Exit
                println("Unexpected value.")
                exitProcess(-1)
            }
        }
        // ˄
    }

    override fun toString(): String {
        // ˅
        return "[money = $money]"
        // ˄
    }

    // ˅
    
    // ˄
}

// ˅

// ˄
