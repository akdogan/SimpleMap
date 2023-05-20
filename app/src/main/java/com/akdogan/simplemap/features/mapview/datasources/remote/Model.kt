package com.akdogan.simplemap.features.mapview.datasources.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// All remote models have a default value, in case the api suddenly changes and fields are removed

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
    @SerialName("location")
    val location: Location? = null,
)

@Serializable
data class Location(
    @SerialName("address")
    val address: String? = null,
    @SerialName("postcode")
    val postcode: String? = null,
    @SerialName("locality")
    val locality: String? = null,
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

@Serializable
data class Photo(
    @SerialName("prefix")
    val prefix: String? = null,
    @SerialName("suffix")
    val suffix: String? = null
)