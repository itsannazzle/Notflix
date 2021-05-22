package com.example.notflix.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowModel(
    var id_tvshow: Int =0,
    var backDrop : String? = null,
    var poster: String? = null,
    var title: String? = null,
    var genre: String? = null,
    var country: String? = null,
    var rating: Double =0.0,
    var overview: String? = null,
    var duration: Int = 0,
    val total_eps: Int =0,
    var favorite : Boolean = false
) : Parcelable
