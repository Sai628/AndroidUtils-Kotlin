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

import android.graphics.Matrix


inline operator fun Matrix.times(m: Matrix): Matrix = Matrix(this).apply { preConcat(m) }

inline fun Matrix.values(): FloatArray = FloatArray(9).apply { getValues(this) }


fun translationMatrix(tx: Float = 0.0f, ty: Float = 0.0f): Matrix = Matrix().apply { setTranslate(tx, ty) }
fun scaleMatrix(sx: Float = 1.0f, sy: Float = 1.0f): Matrix = Matrix().apply { setScale(sx, sy) }
fun rotationMatrix(degrees: Float, px: Float = 0.0f, py: Float = 0.0f): Matrix = Matrix().apply { setRotate(degrees, px, py) }
