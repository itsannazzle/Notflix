package com.example.notflix.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetailMoviesResponse(


	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@field:SerializedName("genres")
	val genres: List<GenresItem>,

	@field:SerializedName("production_countries")
	val productionCountries: List<ProductionCountriesItem>,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("original_title")
	val originalTitle: String? = null,

	@field:SerializedName("runtime")
	val runtime: Int,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("adult")
	val adult: Boolean? = null,



)

data class GenresItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int? = null
)

data class ProductionCountriesItem(

	@field:SerializedName("iso_3166_1")
	val iso31661: String? =null,

	@field:SerializedName("name")
	val name: String
)



