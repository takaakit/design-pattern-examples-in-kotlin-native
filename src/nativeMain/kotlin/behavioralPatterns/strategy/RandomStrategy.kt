package behavioralPatterns.strategy
// ˅
import kotlin.random.Random

// ˄

// Random Strategy: showing a random hand signal.
class RandomStrategy : Strategy {
    // ˅
    // ˄

    override fun showHandSignal(): HandSignal {
        // ˅
        return HandSignal.Constant.getHand(Random.nextInt(3))
        // ˄
    }

    override fun notifyGameResult(result: GameResultType, ownHand: HandSignal, opponentsHand: HandSignal) {
        // ˅
        // Do nothing
        // ˄
    }

    // ˅
    
    // ˄
}

// ˅

// ˄
