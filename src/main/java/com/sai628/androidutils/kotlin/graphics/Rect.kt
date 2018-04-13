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

import android.graphics.*


inline operator fun Rect.component1(): Int = this.left
inline operator fun Rect.component2(): Int = this.top
inline operator fun Rect.component3(): Int = this.right
inline operator fun Rect.component4(): Int = this.bottom

inline operator fun RectF.component1(): Float = this.left
inline operator fun RectF.component2(): Float = this.top
inline operator fun RectF.component3(): Float = this.right
inline operator fun RectF.component4(): Float = this.bottom


inline operator fun Rect.plus(r: Rect): Rect {
    return Rect(this).apply { union(r) }
}


inline operator fun RectF.plus(r: RectF): RectF {
    return RectF(this).apply { union(r) }
}


inline operator fun Rect.plus(xy: Int): Rect {
    return Rect(this).apply { offset(xy, xy) }
}


inline operator fun RectF.plus(xy: Float): RectF {
    return RectF(this).apply { offset(xy, xy) }
}


inline operator fun Rect.plus(p: Point): Rect {
    return Rect(this).apply { offset(p.x, p.y) }
}


inline operator fun RectF.plus(p: PointF): RectF {
    return RectF(this).apply { offset(p.x, p.y) }
}


inline operator fun Rect.minus(r: Rect): Region {
    return Region(this).apply { op(r, Region.Op.DIFFERENCE) }
}


inline operator fun RectF.minus(r: RectF): Region {
    return Region(this.toRect()).apply { op(r.toRect(), Region.Op.DIFFERENCE) }
}


inline operator fun Rect.minus(xy: Int): Rect {
    return Rect(this).apply { offset(-xy, -xy) }
}


inline operator fun RectF.minus(xy: Float): RectF {
    return RectF(this).apply { offset(-xy, -xy) }
}


inline operator fun Rect.minus(p: Point): Rect {
    return Rect(this).apply { offset(-p.x, -p.y) }
}


inline operator fun RectF.minus(p: PointF): RectF {
    return RectF(this).apply { offset(-p.x, -p.y) }
}


inline infix fun Rect.and(r: Rect): Rect = this + r
inline infix fun RectF.and(r: RectF): RectF = this + r


inline infix fun Rect.or(r: Rect): Rect {
    return Rect(this).apply { intersect(r) }
}


inline infix fun RectF.or(r: RectF): RectF {
    return RectF(this).apply { intersect(r) }
}


inline infix fun Rect.xor(r: Rect): Region {
    return Region(this).apply { op(r, Region.Op.XOR) }
}


inline infix fun RectF.xor(r: RectF): Region {
    return Region(this.toRect()).apply { op(r.toRect(), Region.Op.XOR) }
}


inline operator fun Rect.contains(p: Point): Boolean = contains(p.x, p.y)
inline operator fun RectF.contains(p: PointF): Boolean = contains(p.x, p.y)


inline fun Rect.toRectF(): RectF = RectF(this)
inline fun RectF.toRect(): Rect = Rect().also { roundOut(it) }
inline fun Rect.toRegion(): Region = Region(this)
inline fun RectF.toRegion(): Region = Region(this.toRect())


inline fun RectF.transform(m: Matrix) = apply { m.mapRect(this@transform) }
