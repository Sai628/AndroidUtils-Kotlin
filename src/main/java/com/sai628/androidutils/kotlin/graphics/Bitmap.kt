/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("NOTHING_TO_INLINE")

package com.sai628.androidutils.kotlin.graphics

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorSpace
import android.support.annotation.ColorInt
import android.support.annotation.RequiresApi


inline fun Bitmap.applyCanvas(block: Canvas.() -> Unit): Bitmap {
    val c = Canvas(this)
    c.block()
    return this
}


inline operator fun Bitmap.get(x: Int, y: Int): Int = getPixel(x, y)
inline operator fun Bitmap.set(x: Int, y: Int, @ColorInt color: Int) = setPixel(x, y, color)


inline fun Bitmap.scale(
        width: Int,
        height: Int,
        filter: Boolean = true
): Bitmap {
    return Bitmap.createScaledBitmap(this, width, height, filter)
}


inline fun createBitmap(
        width: Int,
        height: Int,
        config: Bitmap.Config = Bitmap.Config.ARGB_8888
): Bitmap {
    return Bitmap.createBitmap(width, height, config)
}


@RequiresApi(26)
inline fun createBitmap(
        width: Int,
        height: Int,
        config: Bitmap.Config = Bitmap.Config.ARGB_8888,
        hasAlpha: Boolean = true,
        colorSpace: ColorSpace = ColorSpace.get(ColorSpace.Named.SRGB)
): Bitmap {
    return Bitmap.createBitmap(width, height, config, hasAlpha, colorSpace)
}
