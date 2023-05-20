package com.akdogan.simplemap.features.mapview.datasources.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface FourSquareApi {

    @GET("v3/places/nearby")
    suspend fun getNearby(
        @Query("ll") queryLocation: String,
        @Query("limit") limit: Int,
        @Query("fields") fields: String
    ): PlacesRemote
}
