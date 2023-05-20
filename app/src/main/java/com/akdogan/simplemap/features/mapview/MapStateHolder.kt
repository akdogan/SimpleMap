package com.akdogan.simplemap.features.mapview

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.akdogan.simplemap.features.mapview.domainmodel.Location
import com.akdogan.simplemap.features.mapview.domainmodel.Point
import com.akdogan.simplemap.features.mapview.domainmodel.toDomain
import org.osmdroid.util.GeoPoint

class MapStateHolder(
    val onSearchClicked: () -> Unit
) {
    val currentCenter: MutableState<GeoPoint?> = mutableStateOf(null)
    val applicationIsActive: MutableState<Boolean> = mutableStateOf(false)
    val locations: SnapshotStateList<Location> = SnapshotStateList()
    val selectedLocation: MutableState<Location?> = mutableStateOf(null)

    val showSearchButton: State<Boolean> = derivedStateOf {
        currentCenter.value != null
    }

    fun getCurrentCenter(): Point? {
        return currentCenter.value?.toDomain()
    }
}

