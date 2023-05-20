package com.akdogan.simplemap.features.mapview.datasources

import com.akdogan.simplemap.BuildConfig
import com.akdogan.simplemap.common.api.HeaderAuthorizationInterceptor
import com.akdogan.simplemap.common.api.getApiInstance
import com.akdogan.simplemap.features.mapview.datasources.remote.FourSquareApi
import com.akdogan.simplemap.features.mapview.domainmodel.Location
import com.akdogan.simplemap.features.mapview.domainmodel.Point
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MapRepository {


    private val api: FourSquareApi = getApiInstance(
        baseUrl = FOURSQUARE_BASE_URL,
        serviceClass = FourSquareApi::class.java,
        interceptors = listOf(HeaderAuthorizationInterceptor(BuildConfig.FOURSQUARE_API_KEY))
    )

    @Throws
    suspend fun getPointsOfInterest(location: Point): List<Location> = withContext(Dispatchers.IO) {
        val result = api.getNearby(queryLocation = location.toQueryParameter(), fields = DEFAULT_FIELDS)
        return@withContext result.toDomain()
    }

    companion object {
        const val FOURSQUARE_BASE_URL = "https://api.foursquare.com/"
        const val DEFAULT_FIELDS = "name,geocodes,website,description,location,photos"
    }
}