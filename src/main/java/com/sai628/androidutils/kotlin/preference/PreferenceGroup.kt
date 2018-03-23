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

package com.sai628.androidutils.kotlin.preference

import android.preference.Preference
import android.preference.PreferenceGroup


inline val PreferenceGroup.size: Int get() = preferenceCount
inline fun PreferenceGroup.isEmpty(): Boolean = size == 0
inline fun PreferenceGroup.isNotEmpty(): Boolean = size != 0

inline operator fun PreferenceGroup.get(key: CharSequence): Preference = findPreference(key)


operator fun PreferenceGroup.get(index: Int): Preference {
    if (index < 0 || index >= preferenceCount) {
        throw IndexOutOfBoundsException("Index: $index, Size: $preferenceCount")
    }
    return getPreference(index)
}


operator fun PreferenceGroup.contains(preference: Preference): Boolean {
    for (index in 0 until size) {
        if (get(index) == preference) {
            return true
        }
    }
    return false
}


inline operator fun PreferenceGroup.plusAssign(preference: Preference) {
    addPreference(preference)
}


inline operator fun PreferenceGroup.minusAssign(preference: Preference) {
    removePreference(preference)
}


inline fun PreferenceGroup.forEach(action: (preference: Preference) -> Unit) {
    for (index in 0 until size) {
        action(get(index))
    }
}


inline fun PreferenceGroup.forEachIndexed(action: (index: Int, preference: Preference) -> Unit) {
    for (index in 0 until size) {
        action(index, get(index))
    }
}


operator fun PreferenceGroup.iterator() = object : MutableIterator<Preference> {
    private var index: Int = 0
    override fun hasNext(): Boolean = index < size
    override fun next(): Preference = getPreference(index++) ?: throw IndexOutOfBoundsException()
    override fun remove() {
        removePreference(getPreference(--index))
    }
}
