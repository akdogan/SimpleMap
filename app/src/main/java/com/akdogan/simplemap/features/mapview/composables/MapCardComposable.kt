package com.akdogan.simplemap.features.mapview.composables

import android.view.LayoutInflater
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akdogan.simplemap.features.mapview.MapStateHolder

@Composable
fun MapCard(
    modifier: Modifier = Modifier,
    mapState: MapStateHolder,
    getLayoutInflater: () -> LayoutInflater
) {
    Box(modifier = modifier) {
        ElevatedCard {
            OsmMap(mapState = mapState, getLayoutInflator = getLayoutInflater)
        }
        if (mapState.showSearchButton.value) {
            // todo animated visibility would be nice
            Button(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .align(Alignment.TopCenter),
                onClick = { mapState.onSearchClicked() })
            {
                // todo hardcoded strings are not translatable
                Text("Search here")
            }
        }
    }
}