package com.example.notflix.entity


class MoviesEntity(
    var id_movies : String,
    var poster : String? = null,
    var title : String? = null,
    var genre : String? = null,
    var country : String? = null,
    var rating : String? = null,
    var overview : String? = null,
    var duration: String? =null,
)