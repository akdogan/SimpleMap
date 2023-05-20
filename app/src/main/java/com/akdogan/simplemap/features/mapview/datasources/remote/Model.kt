package com.akdogan.simplemap.features.mapview.datasources.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlacesRemote(
    @SerialName("results")
    val results: List<PlaceEntityRemote>? = null
)

@Serializable
data class PlaceEntityRemote(
    @SerialName("fsq_id")
    val id: String? = null,
    @SerialName("geocodes")
    val geoLocations: GeoLocations? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("link")
    val link: String? = null,
)

@Serializable
data class GeoLocations(
    @SerialName("main")
    val main: PointRemote? = null
)

@Serializable
data class PointRemote(
    @SerialName("latitude")
    val latitude: Double? = null,
    @SerialName("longitude")
    val longitude: Double? = null
)