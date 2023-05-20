package com.akdogan.simplemap.features.mapview.datasources

import com.akdogan.simplemap.features.mapview.datasources.remote.PlaceEntityRemote
import com.akdogan.simplemap.features.mapview.datasources.remote.PlacesRemote
import com.akdogan.simplemap.features.mapview.domainmodel.Location
import com.akdogan.simplemap.features.mapview.domainmodel.Point

fun Point.toQueryParameter(): String = "${this.latitude},${this.longitude}"

/**
 * Mapping Policy: If a required field is not present, the whole entity is discarded
 */
fun PlacesRemote.toDomain(): List<Location> = results?.mapNotNull {
    it.toDomain()
} ?: emptyList()

fun PlaceEntityRemote.toDomain(): Location? {
    val point = Point(
        latitude = this.geoLocations?.main?.latitude ?: return null,
        longitude = this.geoLocations.main.longitude ?: return null,
    )

    val photoItem = photos?.firstOrNull { !it.prefix.isNullOrBlank() && !it.suffix.isNullOrBlank() }
    val photoLink = if (photoItem != null) {
        "${photoItem.prefix}${MapRepository.DEFAULT_PHOTO_SIZE}${photoItem.suffix}"
    } else {
        null
    }

    return Location(
        // required
        point = point,
        // required
        name = name ?: return null,
        // optional
        link = if (link == null) "" else "${MapRepository.FOURSQUARE_BASE_URL}${link}",
        // optional
        address = this.location?.address.orEmpty(),
        // optional
        city = "${location?.locality.orEmpty()} ${location?.postcode.orEmpty()}".trim(),
        // optional
        photoLink = photoLink
    )
}
