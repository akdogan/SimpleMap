package com.akdogan.simplemap.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.akdogan.simplemap.common.theme.SimpleMapTheme
import com.akdogan.simplemap.features.mapview.MapScreen
import com.akdogan.simplemap.features.mapview.MapViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val mapViewModel: MapViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleMapTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MapScreen(mapState = mapViewModel.mapStateHolder) {
                        layoutInflater
                    }
                }
            }
        }
        mapViewModel.initialLoad()
    }

    override fun onResume() {
        super.onResume()
        mapViewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapViewModel.onPause()
    }
}
