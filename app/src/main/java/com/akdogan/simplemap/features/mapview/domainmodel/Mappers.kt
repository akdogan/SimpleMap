package com.akdogan.simplemap.features.mapview.domainmodel

import org.osmdroid.util.GeoPoint


fun Point.toOsmPoint() = GeoPoint(
    this.latitude, this.longitude
)

fun GeoPoint.toDomain() = Point(
    latitude = this.latitude,
    longitude = this.longitude
)