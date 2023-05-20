package com.akdogan.simplemap.features.mapview

import android.view.LayoutInflater
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akdogan.simplemap.features.mapview.composables.DetailCard
import com.akdogan.simplemap.features.mapview.composables.OsmMap

/**
 * Top level composable
 */
@Composable
fun MapScreen(
    mapState: MapStateHolder,
    getLayoutInflater: () -> LayoutInflater
) {
    Column(modifier = Modifier.fillMaxWidth().animateContentSize()) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(20.dp)
        ) {
            OsmMap(mapState = mapState, getLayoutInflator = getLayoutInflater)
        }
        val selectedLocation = mapState.selectedLocation.value
        if (selectedLocation != null) {
            DetailCard(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                item = selectedLocation
            )
        }
    }
}