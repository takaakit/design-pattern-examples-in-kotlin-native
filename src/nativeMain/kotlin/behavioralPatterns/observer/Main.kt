package behavioralPatterns.observer

import kotlin.random.Random
import platform.posix.usleep

/*
Observers observe objects generating a numerical value and display the value.
The display formats are digits and bar charts.
 */

fun main() {
    val numberSubject = NumberSubject()
    numberSubject.attach(DigitObserver(numberSubject))
    numberSubject.attach(BarChartObserver(numberSubject))

    for (i in 0..19) {
        numberSubject.value = Random.nextInt(50)
        usleep(200000u)
    }
}
