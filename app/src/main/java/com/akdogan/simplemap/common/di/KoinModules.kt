package com.akdogan.simplemap.common.di

import com.akdogan.simplemap.features.mapview.MapViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MapViewModel() }
}
