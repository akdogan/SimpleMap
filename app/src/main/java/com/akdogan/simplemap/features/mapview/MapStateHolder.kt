package com.akdogan.simplemap.features.mapview

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.akdogan.simplemap.features.mapview.domainmodel.Location
import com.akdogan.simplemap.features.mapview.domainmodel.Point
import com.akdogan.simplemap.features.mapview.domainmodel.toDomain
import com.akdogan.simplemap.features.mapview.domainmodel.toOsmPoint
import org.osmdroid.util.GeoPoint

class MapStateHolder {
    val currentCenter: MutableState<GeoPoint?> = mutableStateOf(null)
    val applicationIsActive: MutableState<Boolean> = mutableStateOf(false)
    val locations: SnapshotStateList<Location> = SnapshotStateList()
    val scrollToPoint: MutableState<GeoPoint?> = mutableStateOf(null)
    val selectedLocation: MutableState<Location?> = mutableStateOf(null)

    fun scrollToPoint(point: Point) {
        scrollToPoint.value = point.toOsmPoint()
    }

    fun getCurrentCenter(): Point? {
        return currentCenter.value?.toDomain()
    }
}

