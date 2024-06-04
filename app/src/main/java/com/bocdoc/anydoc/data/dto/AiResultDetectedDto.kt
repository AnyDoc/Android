package com.bocdoc.anydoc.data.dto

data class AiResultDetectedDto(
    val detectedTitle: String,
    val detectedPercent: Float,
    val detectedReason: String,
)