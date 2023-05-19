package com.akdogan.simplemap.features.mapview.datasources

import com.akdogan.simplemap.BuildConfig
import com.akdogan.simplemap.common.api.HeaderAuthorizationInterceptor
import com.akdogan.simplemap.common.api.getApiInstance
import com.akdogan.simplemap.features.mapview.Point
import com.akdogan.simplemap.features.mapview.datasources.remote.FourSquareApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class MapRepository {

    private val baseUrl = "https://api.foursquare.com/"

    private val api: FourSquareApi = getApiInstance(
        baseUrl = baseUrl,
        serviceClass = FourSquareApi::class.java,
        interceptors = listOf(HeaderAuthorizationInterceptor(BuildConfig.FOURSQUARE_API_KEY))
    )

    suspend fun getPointsOfInterest(location: Point) = withContext(Dispatchers.IO) {
        try {
            val result = api.getNearby(location.toQueryParameter())
            Timber.d("Repo remote call done with $result")

        } catch (e: Exception) {
            Timber.w("Call failed with $e", e)
        }
    }
}