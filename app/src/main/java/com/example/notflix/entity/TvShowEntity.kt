package com.example.notflix.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class TvShowEntity (
    var id_tvshow : String,
    var poster : String? = null,
    var title : String? = null,
    var genre : String? = null,
    var country : String? = null,
    var rating : String? = null,
    var overview : String? = null,
    var duration: String? =null,
    val total_eps : String
        ) : Parcelable