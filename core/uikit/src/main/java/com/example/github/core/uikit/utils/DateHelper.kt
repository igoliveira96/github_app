package com.example.github.core.uikit.utils

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"

fun String.toDate(
    dateFormat: String = DEFAULT_DATE_FORMAT,
    timeZone: TimeZone = TimeZone.getTimeZone("UTC"),
): Date? {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    return parser.parse(this)
}

fun Date.isToday(): Boolean {
    return DateUtils.isToday(this.time)
}

fun Date.isDayOfThisWeek(): Boolean {
    val today = LocalDate.now()

    var sunday = today
    while (sunday.dayOfWeek != DayOfWeek.SUNDAY) {
        sunday = sunday.minusDays(1)
    }
    sunday = sunday.minusDays(1)

    var saturday = today
    while (saturday.dayOfWeek != DayOfWeek.SATURDAY) {
        saturday = saturday.plusDays(1)
    }
    saturday = saturday.plusDays(1)

    val currentDate = this.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

    return (currentDate.isAfter(sunday) && currentDate.isBefore(saturday)) || this.isToday()
}

fun Date.toEventTime(): String {
    val currentDate = this.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    val day = currentDate.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
    val time = SimpleDateFormat("HH:mm", Locale.getDefault())
        .format(this).toString()
    return "${day.replaceFirstChar { it.uppercase() }.replace(".", "")}, $time"
}

fun Date.toShortDate(): String {
    return SimpleDateFormat("dd.MM", Locale.getDefault())
        .format(this).toString()
}

fun Date.toLongDate(): String {
    return SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        .format(this).toString()
}

fun LocalDateTime.format(format: String): String {
    val dateformat = DateTimeFormatter.ofPattern(format)
    dateformat.withZone(ZoneId.of("UTC"))
    return this.format(dateformat)
}