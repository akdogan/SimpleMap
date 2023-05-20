package com.akdogan.simplemap.features.mapview.composables

import android.view.LayoutInflater
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.akdogan.simplemap.BuildConfig
import com.akdogan.simplemap.R
import com.akdogan.simplemap.features.mapview.MapStateHolder
import com.akdogan.simplemap.features.mapview.MapViewModel
import com.akdogan.simplemap.features.mapview.domainmodel.Location
import com.akdogan.simplemap.features.mapview.domainmodel.toOsmPoint
import org.osmdroid.config.Configuration
import org.osmdroid.events.MapListener
import org.osmdroid.events.ScrollEvent
import org.osmdroid.events.ZoomEvent
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@Composable
fun OsmMap(
    modifier: Modifier = Modifier,
    mapState: MapStateHolder,
    getLayoutInflator: () -> LayoutInflater,
) {
    AndroidView(
        modifier = modifier,
        factory = {
            // when instantiating osm map directly, the map will overflow into the compose area of
            // the screen when scrolling. Inflate with a wrapping parent layout instead.
            MapView(it)
            val mapLayout = getLayoutInflator().inflate(R.layout.osmmapview, null)

            val mapView = mapLayout.findViewById<MapView>(R.id.actual_map_view)

            Configuration.getInstance().apply {
                userAgentValue = BuildConfig.APPLICATION_ID
            }

            val mapController = mapView.controller
            mapController.setZoom(initialZoomLevel)
            mapController.setCenter(MapViewModel.initialCenter.toOsmPoint())
            mapView.minZoomLevel = minZoomLevel
            mapView.isTilesScaledToDpi = true

            mapView.setUpdateMapCenterListener { center ->
                mapState.currentCenter.value = center
            }

            return@AndroidView mapLayout
        },
        update = {
            val mapView = it.findViewById<MapView>(R.id.actual_map_view)
            // osm map documentation says we need to pause / resume the map
            if (mapState.applicationIsActive.value) {
                mapView.onResume()
            } else {
                mapView.onPause()
            }

            mapView.addPins(mapState.locations.toList()) { selectedLocation ->
                mapState.selectedLocation.value = selectedLocation
            }
            mapView.invalidate()
        }
    )
}

private fun MapView.setUpdateMapCenterListener(
    onCenterUpdated: (GeoPoint) -> Unit
) {
    val listener = object : MapListener {
        override fun onScroll(event: ScrollEvent?): Boolean {
            onCenterUpdated(
                GeoPoint(
                    this@setUpdateMapCenterListener.mapCenter.latitude,
                    this@setUpdateMapCenterListener.mapCenter.longitude
                )
            )
            return true
        }

        override fun onZoom(event: ZoomEvent?): Boolean {
            onCenterUpdated(
                GeoPoint(
                    this@setUpdateMapCenterListener.mapCenter.latitude,
                    this@setUpdateMapCenterListener.mapCenter.longitude
                )
            )
            return true
        }
    }
    addMapListener(listener)
}

private fun MapView.addPins(
    locationList: List<Location>,
    onMarkerClicked: (Location) -> Unit
) {
    locationList.forEach {
        val marker = Marker(this)
        marker.position = GeoPoint(it.point.latitude, it.point.longitude)
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        marker.title = it.name
        marker.icon = ContextCompat.getDrawable(context, R.drawable.icon_location_marker)
        DrawableCompat.setTint(marker.icon, ContextCompat.getColor(context, R.color.pastel_red))

        marker.setOnMarkerClickListener { currentMarker, mapView ->
            onMarkerClicked(it)
            // reset in case another marker was selected.
            // Todo: maybe remember which one was selected on only reset that one
            mapView.resetAllMarkers()

            // set focused style
            DrawableCompat.setTint(
                currentMarker.icon,
                ContextCompat.getColor(context, R.color.aquamarine)
            )

            mapView.controller.animateTo(
                currentMarker.position,
                selectedZoomLevel,
                moveToAnimationDuration
            )
            mapView.invalidate()
            true
        }
        this.overlays.add(marker)
    }
}

private fun MapView.resetAllMarkers() {
    overlays.toList().forEach {
        val marker = it as? Marker ?: return@forEach
        DrawableCompat.setTint(marker.icon, ContextCompat.getColor(context, R.color.pastel_red))
    }
}

private const val selectedZoomLevel: Double = 18.0
private const val initialZoomLevel: Double = 17.0
private const val minZoomLevel: Double = 14.0
private const val moveToAnimationDuration: Long = 300L
