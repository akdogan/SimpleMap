package com.akdogan.simplemap.common.di

import com.akdogan.simplemap.features.mapview.MapViewModel
import com.akdogan.simplemap.features.mapview.datasources.MapRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::MapRepository) { bind() }
    viewModel { MapViewModel(get()) }
}
