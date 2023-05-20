package com.akdogan.simplemap.features.mapview

import android.view.LayoutInflater
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akdogan.simplemap.features.mapview.composables.OsmMap

/**
 * Top level composable
 */
@Composable
fun MapScreen(
    mapState: MapStateHolder,
    getLayoutInflater: () -> LayoutInflater
){
    Column(modifier = Modifier.fillMaxWidth()) {
        val center = mapState.currentCenter.value
        Text("Location: ${center?.latitude} | ${center?.longitude}")
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        OsmMap(mapState = mapState, getLayoutInflator = getLayoutInflater)
    }}
}