package com.akdogan.simplemap.features.mapview.domainmodel

data class Location(
    val point: Point,
    val name: String,
    val link: String,
    val address: String,
    val city: String
)
