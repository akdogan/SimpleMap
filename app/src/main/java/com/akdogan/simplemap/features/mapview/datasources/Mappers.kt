package com.akdogan.simplemap.features.mapview.datasources

import com.akdogan.simplemap.features.mapview.Point

fun Point.toQueryParameter(): String = "${this.latitude},${this.longitude}"