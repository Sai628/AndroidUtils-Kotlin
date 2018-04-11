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

package com.sai628.androidutils.kotlin.db

import android.database.Cursor


inline fun Cursor.getBlob(columnName: String): ByteArray = getBlob(getColumnIndexOrThrow(columnName))

inline fun Cursor.getDouble(columnName: String): Double = getDouble(getColumnIndexOrThrow(columnName))

inline fun Cursor.getFloat(columnName: String): Float = getFloat(getColumnIndexOrThrow(columnName))

inline fun Cursor.getInt(columnName: String): Int = getInt(getColumnIndexOrThrow(columnName))

inline fun Cursor.getLong(columnName: String): Long = getLong(getColumnIndexOrThrow(columnName))

inline fun Cursor.getShort(columnName: String): Short = getShort(getColumnIndexOrThrow(columnName))

inline fun Cursor.getString(columnName: String): String = getString(getColumnIndexOrThrow(columnName))

inline fun Cursor.getBlobOrNull(index: Int): ByteArray? = if (isNull(index)) null else getBlob(index)

inline fun Cursor.getDoubleOrNull(index: Int): Double? = if (isNull(index)) null else getDouble(index)

inline fun Cursor.getFloatOrNull(index: Int): Float? = if (isNull(index)) null else getFloat(index)

inline fun Cursor.getIntOrNull(index: Int): Int? = if (isNull(index)) null else getInt(index)

inline fun Cursor.getLongOrNull(index: Int): Long? = if (isNull(index)) null else getLong(index)

inline fun Cursor.getShortOrNull(index: Int): Short? = if (isNull(index)) null else getShort(index)

inline fun Cursor.getStringOrNull(index: Int): String? = if (isNull(index)) null else getString(index)

inline fun Cursor.getBlobOrNull(columnName: String): ByteArray? =
        getColumnIndexOrThrow(columnName).let { if (isNull(it)) null else getBlob(it) }

inline fun Cursor.getDoubleOrNull(columnName: String): Double? =
        getColumnIndexOrThrow(columnName).let { if (isNull(it)) null else getDouble(it) }

inline fun Cursor.getFloatOrNull(columnName: String): Float? =
        getColumnIndexOrThrow(columnName).let { if (isNull(it)) null else getFloat(it) }

inline fun Cursor.getIntOrNull(columnName: String): Int? =
        getColumnIndexOrThrow(columnName).let { if (isNull(it)) null else getInt(it) }

inline fun Cursor.getLongOrNull(columnName: String): Long? =
        getColumnIndexOrThrow(columnName).let { if (isNull(it)) null else getLong(it) }

inline fun Cursor.getShortOrNull(columnName: String): Short? =
        getColumnIndexOrThrow(columnName).let { if (isNull(it)) null else getShort(it) }

inline fun Cursor.getStringOrNull(columnName: String): String? =
        getColumnIndexOrThrow(columnName).let { if (isNull(it)) null else getString(it) }
