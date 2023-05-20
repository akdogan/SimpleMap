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

    private val baseUrl = "https://api.foursquare.com/"

    private val api: FourSquareApi = getApiInstance(
        baseUrl = baseUrl,
        serviceClass = FourSquareApi::class.java,
        interceptors = listOf(HeaderAuthorizationInterceptor(BuildConfig.FOURSQUARE_API_KEY))
    )

    @Throws
    suspend fun getPointsOfInterest(location: Point): List<Location> = withContext(Dispatchers.IO) {
        val result = api.getNearby(location.toQueryParameter())
        return@withContext result.toDomain()
    }
}