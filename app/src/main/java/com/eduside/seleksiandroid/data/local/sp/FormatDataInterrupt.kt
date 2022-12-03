package com.eduside.seleksiandroid.data.local.sp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FormatDataInterrupt(
    var id: String? = "",
    var interrupt: String? = ""
): Parcelable