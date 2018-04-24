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

import android.graphics.Path
import android.graphics.PointF
import android.support.annotation.RequiresApi


data class PathSegment(
        val start: PointF,
        val startFraction: Float,
        val end: PointF,
        val endFraction: Float
)


@RequiresApi(26)
fun Path.flatten(error: Float = 0.5f): Iterable<PathSegment> {

    val pathData = approximate(error)
    val pointCount = pathData.size / 3
    val segments = ArrayList<PathSegment>(pointCount / 2)

    for (i in 1 until pointCount) {
        val index = i * 3
        val prevIndex = (i - 1) * 3

        val d = pathData[index]
        val x = pathData[index + 1]
        val y = pathData[index + 2]

        val pd = pathData[prevIndex]
        val px = pathData[prevIndex + 1]
        val py = pathData[prevIndex + 2]

        if (d != pd && (x != px || y != py)) {
            segments.add(PathSegment(PointF(px, py), pd, PointF(x, y), d))
        }
    }

    return segments
}


@RequiresApi(19)
inline operator fun Path.plus(p: Path): Path {
    return Path(this).apply {
        op(p, Path.Op.UNION)
    }
}


@RequiresApi(19)
inline operator fun Path.minus(p: Path): Path {
    return Path(this).apply {
        op(p, Path.Op.DIFFERENCE)
    }
}


@RequiresApi(19)
inline infix fun Path.and(p: Path): Path = this + p


@RequiresApi(19)
inline infix fun Path.or(p: Path): Path {
    return Path().apply {
        op(this@or, p, Path.Op.INTERSECT)
    }
}


@RequiresApi(19)
inline infix fun Path.xor(p: Path): Path {
    return Path(this).apply {
        op(p, Path.Op.XOR)
    }
}
