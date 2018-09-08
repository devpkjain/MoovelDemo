package com.pkjain.demo.model

/**
 *This class defines fare info
 */

data class RiderInfo(val name: String, val fares: Fares)

data class Rider(val Adult: Fares, val Child: Fares, val Senior: Fares)

data class Fares(val fares: List<Fare>, val subtext: String?)

data class Fare(val description: String, val price: Float)