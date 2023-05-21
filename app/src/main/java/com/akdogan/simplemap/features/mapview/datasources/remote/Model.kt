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
    val geoLocations: GeoLocationsRemote? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("link")
    val link: String? = null,
    @SerialName("location")
    val location: LocationRemote? = null,
    @SerialName("photos")
    val photos: List<PhotoRemote>? = null
)

@Serializable
data class LocationRemote(
    @SerialName("address")
    val address: String? = null,
    @SerialName("postcode")
    val postcode: String? = null,
    @SerialName("locality")
    val locality: String? = null
)

@Serializable
data class GeoLocationsRemote(
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
data class PhotoRemote(
    @SerialName("prefix")
    val prefix: String? = null,
    @SerialName("suffix")
    val suffix: String? = null
)