package com.akdogan.simplemap.features.mapview.datasources

import com.akdogan.simplemap.features.mapview.domainmodel.Point
import com.google.common.truth.Truth
import org.junit.Test

class PointToQueryParameterTest {

    @Test
    fun `test regular point to queryparameter`() {
        // arrange
        val point = Point(
            latitude = 52.500342,
            longitude = 13.425171
        )
        val expectedResult = "52.500342,13.425171"

        // act
        val result = point.toQueryParameter()

        //assert
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `test short point to queryparameter`() {
        // arrange
        val point = Point(
            latitude = 52.501,
            longitude = 13.425
        )
        val expectedResult = "52.501,13.425"

        // act
        val result = point.toQueryParameter()

        //assert
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `test negative point to queryparameter`() {
        // arrange
        val point = Point(
            latitude = -22.500421,
            longitude = -13.425069
        )
        val expectedResult = "-22.500421,-13.425069"

        // act
        val result = point.toQueryParameter()

        //assert
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `test trailing zeros removed`() {
        // arrange
        val point = Point(
            latitude = 22.500000,
            longitude = 13.425000
        )
        val expectedResult = "22.5,13.425"

        // act
        val result = point.toQueryParameter()

        //assert
        Truth.assertThat(result).isEqualTo(expectedResult)
    }
}