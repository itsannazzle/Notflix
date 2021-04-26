package com.example.notflix.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class PrevTVEntity (
        var tv_id : Int = 0,
        var poster : String? = null
        ) : Parcelable