package com.tourism.kavora.util

import com.tourism.core.model.ThemeMode


fun resolveTheme(mode: ThemeMode): Boolean {
    return when (mode) {
        ThemeMode.DARK -> true
        ThemeMode.LIGHT -> false
        ThemeMode.SYSTEM -> false
    }
}