package com.akdogan.simplemap.features.mapview.composables

import android.content.ClipData
import android.content.ClipboardManager
import androidx.core.content.getSystemService
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akdogan.simplemap.common.theme.SimpleMapTheme
import com.akdogan.simplemap.features.mapview.domainmodel.Location
import com.akdogan.simplemap.features.mapview.domainmodel.Point

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailCard(
    modifier: Modifier = Modifier,
    item: Location
) {
    val application = LocalContext.current.applicationContext
    ElevatedCard(modifier = modifier
        .fillMaxWidth()
        .background(shape = RoundedCornerShape(6.dp), color = Color.White),
        onClick = {
            val clipboardManager = application.getSystemService<ClipboardManager>()
            val clip: ClipData = ClipData.newPlainText("Label", item.link)
            clipboardManager?.setPrimaryClip(clip)
            // todo show a toast if os is < Android 13 (from Android 13 on clipboard manager is shown)
        }
    ) {
        Row(modifier = Modifier.padding(12.dp)){
            Column {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.headlineSmall
//                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = item.address,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = item.city,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DetailCardPreview() {
    SimpleMapTheme {
        Column(modifier = Modifier
            .fillMaxSize()
        ) {
            val item = Location(
                point = Point(0.0,0.0),
                name = "Super nices cafe",
                link = "http://www.google.com",
                address = "Neue Strasse 1",
                city = "Berlin 10179"
            )
            DetailCard(
                modifier = Modifier.padding(10.dp),
                item = item
            )
        }
    }
}