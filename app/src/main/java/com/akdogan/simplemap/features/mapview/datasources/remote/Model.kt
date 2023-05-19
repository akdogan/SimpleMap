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
    val id: String? = null
)