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

package com.sai628.androidutils.kotlin.view

import android.support.annotation.Px
import android.support.annotation.RequiresApi
import android.view.View
import android.view.ViewGroup


operator fun ViewGroup.get(index: Int): View {
    return getChildAt(index) ?: throw IndexOutOfBoundsException("Index: $index, Size: $childCount")
}

inline operator fun ViewGroup.contains(view: View): Boolean = indexOfChild(view) != -1
inline operator fun ViewGroup.plusAssign(view: View) = addView(view)
inline operator fun ViewGroup.minusAssign(view: View) = removeView(view)

inline val ViewGroup.size get() = childCount
inline fun ViewGroup.isEmpty(): Boolean = childCount == 0
inline fun ViewGroup.isNotEmpty(): Boolean = childCount != 0


inline fun ViewGroup.forEach(action: (view: View) -> Unit) {
    for (index in 0 until childCount) {
        action(getChildAt(index))
    }
}


inline fun ViewGroup.forEachIndexed(action: (index: Int, view: View) -> Unit) {
    for (index in 0 until childCount) {
        action(index, getChildAt(index))
    }
}


operator fun ViewGroup.iterator() = object : MutableIterator<View> {
    private var index = 0
    override fun hasNext(): Boolean = index < childCount
    override fun next(): View = getChildAt(index++) ?: throw IndexOutOfBoundsException()
    override fun remove() = removeViewAt(--index)
}


val ViewGroup.children: Sequence<View>
    get() = object : Sequence<View> {
        override fun iterator() = this@children.iterator()
    }


inline fun ViewGroup.MarginLayoutParams.setMargins(@Px size: Int) {
    setMargins(size, size, size, size)
}


inline fun ViewGroup.MarginLayoutParams.updateMargins(
        @Px left: Int = leftMargin,
        @Px top: Int = topMargin,
        @Px right: Int = rightMargin,
        @Px bottom: Int = bottomMargin) {
    setMargins(left, top, right, bottom)
}


@RequiresApi(17)
inline fun ViewGroup.MarginLayoutParams.updateMarginsRelative(
        @Px start: Int = marginStart,
        @Px top: Int = topMargin,
        @Px end: Int = marginEnd,
        @Px bottom: Int = bottomMargin) {
    marginStart = start
    topMargin = top
    marginEnd = end
    bottomMargin = bottom
}
