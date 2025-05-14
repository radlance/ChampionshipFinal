package com.radlance.championshipfinal.presentation.core

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.github.barteksc.pdfviewer.PDFView
import java.io.File

@Composable
fun PdfViewerFromAssets(assetName: String) {
    val context = LocalContext.current
    val pdfFile = remember { copyAssetToTempFile(context, assetName) }

    if (pdfFile != null) {
        AndroidView(
            factory = { ctx ->
                PDFView(ctx, null).apply {
                    fromFile(pdfFile)
                        .enableSwipe(true)
                        .swipeHorizontal(false)
                        .enableDoubletap(true)
                        .defaultPage(0)
                        .load()
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    } else {
        Text("Failed to load PDF")
    }
}

fun copyAssetToTempFile(context: Context, assetName: String): File? {
    return context.assets.open(assetName).use { inputStream ->
        val tempFile = File.createTempFile("temp_pdf", ".pdf", context.cacheDir)
        tempFile.outputStream().use { output ->
            inputStream.copyTo(output)
        }
        tempFile
    }
}