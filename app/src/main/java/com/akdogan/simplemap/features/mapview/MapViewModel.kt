package com.akdogan.simplemap.features.mapview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akdogan.simplemap.features.mapview.datasources.MapRepository
import com.akdogan.simplemap.features.mapview.domainmodel.Point
import kotlinx.coroutines.launch
import timber.log.Timber

class MapViewModel(
    private val mapRepository: MapRepository
) : ViewModel() {

    val mapStateHolder = MapStateHolder {
        onSearchClicked()
    }

    fun initialLoad() {
        loadData(initialCenter)
    }

    private fun loadData(point: Point) {
        viewModelScope.launch {
            try {
                val result = mapRepository.getPointsOfInterest(point)
                mapStateHolder.locations.addAll(result)
            } catch (e: Exception) {
                // todo show a snackbar to the user if retrieving data fails
                Timber.w("Call to retrieve locations failed: $e")
            }
        }
    }

    private fun onSearchClicked() {
        val point = mapStateHolder.getCurrentCenter() ?: return
        loadData(point)
        mapStateHolder.currentCenter.value = null
        mapStateHolder.selectedLocation.value = null
    }

    fun onResume() {
        mapStateHolder.applicationIsActive.value = true
    }

    fun onPause() {
        mapStateHolder.applicationIsActive.value = false
    }

    companion object {
        val initialCenter: Point = Point(
            latitude = 52.500342,
            longitude = 13.425170
        )
    }
}