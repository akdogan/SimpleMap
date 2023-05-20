package com.akdogan.simplemap.features.mapview

import android.view.LayoutInflater
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akdogan.simplemap.features.mapview.composables.DetailCard
import com.akdogan.simplemap.features.mapview.composables.MapCard

/**
 * Top level composable
 */
@Composable
fun MapScreen(
    mapState: MapStateHolder,
    getLayoutInflater: () -> LayoutInflater
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        MapCard(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(20.dp),
            mapState = mapState,
            getLayoutInflater = getLayoutInflater
        )
        val selectedLocation = mapState.selectedLocation.value
        if (selectedLocation != null) {
            // todo animations would be nice
            DetailCard(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                item = selectedLocation
            )
        }
    }
}