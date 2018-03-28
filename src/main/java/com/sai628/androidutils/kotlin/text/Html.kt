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

package com.sai628.androidutils.kotlin.text

import android.annotation.SuppressLint
import android.os.Build
import android.text.Html
import android.text.Spanned


fun String.parseAsHtml(
        @SuppressLint("InlinedApi")
        flag: Int = Html.FROM_HTML_MODE_LEGACY,
        imageGetter: Html.ImageGetter? = null,
        tagHandler: Html.TagHandler? = null
): Spanned {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(this, flag, imageGetter, tagHandler)
    }

    @Suppress("Deprecation")
    return Html.fromHtml(this, imageGetter, tagHandler)
}


fun Spanned.toHtml(
        @SuppressLint("InlinedApi")
        option: Int = Html.TO_HTML_PARAGRAPH_LINES_CONSECUTIVE
): String {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.toHtml(this, option)
    }

    @Suppress("Deprecation")
    return Html.toHtml(this)
}
