package com.akdogan.simplemap.features.mapview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akdogan.simplemap.features.mapview.datasources.MapRepository
import kotlinx.coroutines.launch

class MapViewModel(
    private val mapRepository: MapRepository
) : ViewModel() {

    fun test() {
        viewModelScope.launch {
            mapRepository.getPointsOfInterest()
        }
    }
}