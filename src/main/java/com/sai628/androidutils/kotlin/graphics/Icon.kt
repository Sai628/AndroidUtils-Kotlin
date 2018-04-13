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
import android.graphics.drawable.Icon
import android.net.Uri
import android.support.annotation.RequiresApi


@RequiresApi(26)
inline fun Bitmap.toAdaptiveIcon(): Icon = Icon.createWithAdaptiveBitmap(this)


@RequiresApi(26)
inline fun Bitmap.toIcon(): Icon = Icon.createWithBitmap(this)


@RequiresApi(26)
inline fun Uri.toIcon(): Icon = Icon.createWithContentUri(this)


@RequiresApi(26)
inline fun ByteArray.toIcon(): Icon = Icon.createWithData(this, 0, size)
