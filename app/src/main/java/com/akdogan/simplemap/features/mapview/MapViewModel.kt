package com.akdogan.simplemap.features.mapview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akdogan.simplemap.features.mapview.datasources.MapRepository
import kotlinx.coroutines.launch

class MapViewModel(
    private val mapRepository: MapRepository
) : ViewModel() {

    private var currentCenter: Point = Point(
        latitude = 52.500342,
        longitude = 13.425170
    )

    fun test() {
        viewModelScope.launch {
            mapRepository.getPointsOfInterest(currentCenter)
        }
    }
}