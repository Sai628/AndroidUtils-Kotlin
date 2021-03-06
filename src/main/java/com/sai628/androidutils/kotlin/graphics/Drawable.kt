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

package com.sai628.androidutils.kotlin.graphics

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.annotation.Px


fun Drawable.toBitmap(
        @Px width: Int = intrinsicWidth,
        @Px height: Int = intrinsicHeight,
        config: Bitmap.Config? = null
): Bitmap {
    if (this is BitmapDrawable) {
        if (config == null || bitmap.config == null) {
            if (width == intrinsicWidth && height == intrinsicHeight) {
                return bitmap
            }
            return Bitmap.createScaledBitmap(bitmap, width, height, true)
        }
    }

    val (oldLeft, oldTop, oldRight, oldBottom) = bounds
    val bitmap = Bitmap.createBitmap(width, height, config ?: Bitmap.Config.ARGB_8888)
    setBounds(0, 0, width, height)
    draw(Canvas(bitmap))
    setBounds(oldLeft, oldTop, oldRight, oldBottom)

    return bitmap
}


fun Drawable.updateBounds(
        @Px left: Int = bounds.left,
        @Px top: Int = bounds.top,
        @Px right: Int = bounds.right,
        @Px bottom: Int = bounds.bottom
) {
    setBounds(left, top, right, bottom)
}
