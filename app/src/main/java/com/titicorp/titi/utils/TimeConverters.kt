package com.titicorp.titi.utils

import java.text.SimpleDateFormat
import java.util.Locale

private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())


fun Long.toReadableTime(): String {
    return dateFormat.format(this * 1000)
}