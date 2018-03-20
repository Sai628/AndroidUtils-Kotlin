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

import android.view.Menu
import android.view.MenuItem


inline val Menu.size: Int get() = size()
inline fun Menu.isEmpty(): Boolean = size() == 0
inline fun Menu.isNotEmpty(): Boolean = size() != 0


inline fun Menu.forEach(action: (item: MenuItem) -> Unit) {
    for (index in 0 until size()) {
        action(getItem(index))
    }
}


inline fun Menu.forEachIndexed(action: (index: Int, item: MenuItem) -> Unit) {
    for (index in 0 until size()) {
        action(index, getItem(index))
    }
}


inline operator fun Menu.get(index: Int): MenuItem = getItem(index)


operator fun Menu.contains(item: MenuItem): Boolean {
    for (index in 0 until size()) {
        if (getItem(index) == item) {
            return true
        }
    }
    return false
}


operator fun Menu.iterator() = object : MutableIterator<MenuItem> {
    private var index = 0
    override fun hasNext(): Boolean = index < size()
    override fun next(): MenuItem = getItem(index++) ?: throw IndexOutOfBoundsException()
    override fun remove() = removeItem(--index)
}
