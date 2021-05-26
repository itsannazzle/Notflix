package com.nextint.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesModel(
    var id_movies : Int =0,
    var backDrop : String? = null,
    var poster : String? = null,
    var title : String? = null,
    var genre : String? = null,
    var country : String? = null,
    var rating : Double = 0.0,
    var overview : String? = null,
    var duration: Int = 0,
    var favorite :Boolean = false
) : Parcelable
