package com.ecopedia.ecopedia_android.base.compose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.svg.SvgDecoder

private fun Modifier.applySize(size: Dp? = null, width: Dp? = null, height: Dp? = null): Modifier {
    var m = this
    if (size != null) m = m.size(size)
    else {
        if (width != null) m = m.width(width)
        if (height != null) m = m.height(height)
    }
    return m
}

@Composable
fun SvgImage(
    url: String,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    size: Dp? = null,
    width: Dp? = null,
    height: Dp? = null,
    contentScale: ContentScale = ContentScale.Fit
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components { add(SvgDecoder.Factory()) }
        .build()

    AsyncImage(
        model = ImageRequest.Builder(context).data(url).build(),
        contentDescription = contentDescription,
        imageLoader = imageLoader,
        contentScale = contentScale,
        modifier = modifier.applySize(size, width, height)
    )
}

@Composable
fun UrlImage(
    url: String,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    size: Dp? = null,
    width: Dp? = null,
    height: Dp? = null,
    placeholder: Painter? = ColorPainter(LightGray30),
    error: Painter? = ColorPainter(LightGray30),
    contentScale: ContentScale = ContentScale.Crop,
) {
    val context = LocalContext.current
    AsyncImage(
        model = ImageRequest.Builder(context)
            .listener(
                onError = { request, result ->
                    Log.e("ImageError", "Failed to load image: ${result.throwable}")
                }
            )
            .data(url)
            .build(),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier.applySize(size, width, height),
        placeholder = placeholder,
        error = error

    )
}

@Composable
fun DrawableImage(
    resId: Int,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    size: Dp? = null,
    width: Dp? = null,
    height: Dp? = null,
    contentScale: ContentScale = ContentScale.Fit
) {
    Image(
        painter = painterResource(id = resId),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier.applySize(size, width, height)
    )
}

