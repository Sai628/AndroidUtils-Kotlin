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

package com.sai628.androidutils.kotlin.util

import android.support.annotation.RequiresApi
import android.util.Size
import android.util.SizeF


@RequiresApi(21)
inline operator fun Size.component1() = width
@RequiresApi(21)
inline operator fun Size.component2() = height
@RequiresApi(21)
inline operator fun SizeF.component1() = width
@RequiresApi(21)
inline operator fun SizeF.component2() = height