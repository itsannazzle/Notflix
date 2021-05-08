package com.example.notflix.data.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "moviesTable")
@Parcelize
class MoviesEntity(
    @PrimaryKey
    @NonNull
    var id_movies : Int =0,
    var backDrop : String? = null,
    var poster : String? = null,
    var title : String? = null,
    var genre : String? = null,
    var country : String? = null,
    var rating : Double = 0.0,
    var overview : String? = null,
    var duration: Int = 0,
) : Parcelable