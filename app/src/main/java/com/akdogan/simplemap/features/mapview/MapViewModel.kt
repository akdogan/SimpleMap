package com.akdogan.simplemap.features.mapview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akdogan.simplemap.features.mapview.datasources.MapRepository
import com.akdogan.simplemap.features.mapview.domainmodel.Location
import com.akdogan.simplemap.features.mapview.domainmodel.Point
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class MapViewModel(
    private val mapRepository: MapRepository
) : ViewModel() {

    private var initialCenter: Point = Point(
        latitude = 52.500342,
        longitude = 13.425170
    )

    val mapStateHolder = MapStateHolder()

    fun loadData() {
        viewModelScope.launch {
            Timber.d("Adding test locations")
            mapStateHolder.scrollToPoint(initialCenter)

            try {
                val result = mapRepository.getPointsOfInterest(initialCenter)
                mapStateHolder.locations.addAll(result)
            } catch (e: Exception) {
                // todo show a snackbar to the user if retrieving data fails
                Timber.w("Call to retrieve locations failed: $e")
            }
        }
    }

    fun onResume() {
        mapStateHolder.applicationIsActive.value = true
    }

    fun onPause() {
        mapStateHolder.applicationIsActive.value = false
    }
}