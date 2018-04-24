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
import android.graphics.Rect
import android.graphics.Region
import android.graphics.RegionIterator


inline operator fun Region.contains(p: Point): Boolean = contains(p.x, p.y)


inline operator fun Region.plus(r: Rect): Region {
    return Region(this).apply {
        union(r)
    }
}


inline operator fun Region.plus(r: Region): Region {
    return Region(this).apply {
        op(r, Region.Op.UNION)
    }
}


inline operator fun Region.minus(r: Rect): Region {
    return Region(this).apply {
        op(r, Region.Op.DIFFERENCE)
    }
}


inline operator fun Region.minus(r: Region): Region {
    return Region(this).apply {
        op(r, Region.Op.DIFFERENCE)
    }
}


inline operator fun Region.unaryMinus(): Region {
    return Region(bounds).apply {
        op(this@unaryMinus, Region.Op.DIFFERENCE)
    }
}


inline operator fun Region.not() = -this


inline infix fun Region.and(r: Rect) = this + r
inline infix fun Region.and(r: Region) = this + r


inline infix fun Region.or(r: Rect): Region {
    return Region(this).apply {
        op(r, Region.Op.INTERSECT)
    }
}


inline infix fun Region.or(r: Region): Region {
    return Region(this).apply {
        op(r, Region.Op.INTERSECT)
    }
}


inline infix fun Region.xor(r: Rect): Region {
    return Region(this).apply {
        op(r, Region.Op.XOR)
    }
}


inline infix fun Region.xor(r: Region): Region {
    return Region(this).apply {
        op(r, Region.Op.XOR)
    }
}


inline fun Region.forEach(action: (rect: Rect) -> Unit) {
    val iterator = RegionIterator(this)
    while (true) {
        val r = Rect()
        if (!iterator.next(r)) {
            break
        }
        action(r)
    }
}


operator fun Region.iterator() = object : Iterator<Rect> {

    private val iterator = RegionIterator(this@iterator)
    private val rect = Rect()
    private var hasMore = iterator.next(rect)


    override fun hasNext(): Boolean = hasMore


    override fun next(): Rect {
        if (hasMore) {
            val r = Rect(rect)
            hasMore = iterator.next(rect)
            return r
        }
        throw IndexOutOfBoundsException()
    }
}
