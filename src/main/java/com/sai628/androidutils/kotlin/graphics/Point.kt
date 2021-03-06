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

import android.graphics.Point
import android.graphics.PointF


inline operator fun Point.component1() = this.x
inline operator fun Point.component2() = this.y
inline operator fun PointF.component1() = this.x
inline operator fun PointF.component2() = this.y


inline operator fun Point.plus(p: Point): Point {
    return Point(x, y).apply {
        offset(p.x, p.y)
    }
}


inline operator fun PointF.plus(p: PointF): PointF {
    return PointF(x, y).apply {
        offset(p.x, p.y)
    }
}


inline operator fun Point.plus(xy: Int): Point {
    return Point(x, y).apply {
        offset(xy, xy)
    }
}


inline operator fun PointF.plus(xy: Float): PointF {
    return PointF(x, y).apply {
        offset(xy, xy)
    }
}


inline operator fun Point.minus(p: Point): Point {
    return Point(x, y).apply {
        offset(-p.x, -p.y)
    }
}


inline operator fun PointF.minus(p: PointF): PointF {
    return PointF(x, y).apply {
        offset(-p.x, -p.y)
    }
}


inline operator fun Point.minus(xy: Int): Point {
    return Point(x, y).apply {
        offset(-xy, -xy)
    }
}


inline operator fun PointF.minus(xy: Float): PointF {
    return PointF(x, y).apply {
        offset(-xy, -xy)
    }
}


inline operator fun Point.unaryMinus(): Point = Point(-x, -y)
inline operator fun PointF.unaryMinus(): PointF = PointF(-x, -y)


inline fun Point.toPointF(): PointF = PointF(this)
inline fun PointF.toPoint(): Point = Point(x.toInt(), y.toInt())
