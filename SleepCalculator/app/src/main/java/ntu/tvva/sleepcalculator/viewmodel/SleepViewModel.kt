package ntu.tvva.sleepcalculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.ArrayList

class SleepViewModel : ViewModel() {
    private val _sleepTimes = MutableLiveData<List<String>>()
    val sleepTimes: LiveData<List<String>> = _sleepTimes

    private val _wakeUpTimes = MutableLiveData<List<String>>()
    val wakeUpTimes: LiveData<List<String>> = _wakeUpTimes

    fun calculateSleepTimes(wakeUpHour: Int, wakeUpMinute: Int) {
        val times = ArrayList<String>()
        times.add(formatTime(wakeUpHour, wakeUpMinute))

        val totalWakeUpMinutes = wakeUpHour * 60 + wakeUpMinute

        for (cycles in 8 downTo 3) {
            val totalSleepMinutes = cycles * 90 + 15
            var totalSleepTime = totalWakeUpMinutes - totalSleepMinutes
            
            if (totalSleepTime < 0) {
                totalSleepTime += 24 * 60
            }
            
            val sleepHour = totalSleepTime / 60
            val sleepMinute = totalSleepTime % 60
            
            times.add("${formatTime(sleepHour, sleepMinute)} ($cycles cycles)")
        }

        _sleepTimes.value = times
    }

    fun calculateWakeUpTimes(sleepHour: Int, sleepMinute: Int) {
        val times = ArrayList<String>()
        times.add(formatTime(sleepHour, sleepMinute))

        val totalSleepMinutes = sleepHour * 60 + sleepMinute

        for (cycles in 3..8) {
            val totalSleepDuration = cycles * 90 + 15
            var totalWakeUpTime = totalSleepMinutes + totalSleepDuration
            
            if (totalWakeUpTime >= 24 * 60) {
                totalWakeUpTime -= 24 * 60
            }
            
            val wakeUpHour = totalWakeUpTime / 60
            val wakeUpMinute = totalWakeUpTime % 60
            
            times.add("${formatTime(wakeUpHour, wakeUpMinute)} ($cycles cycles)")
        }

        _wakeUpTimes.value = times
    }

    private fun formatTime(hour: Int, minute: Int): String {
        val amPm = if (hour >= 12) "PM" else "AM"
        var displayHour = hour
        if (hour > 12) displayHour -= 12
        if (hour == 0) displayHour = 12
        return String.format("%d:%02d %s", displayHour, minute, amPm)
    }
} 