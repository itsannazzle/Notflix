package com.nextint.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<TVResultsItem>,

	@field:SerializedName("total_results")
	val totalResults: Int
)

data class TVResultsItem(

	@field:SerializedName("first_air_date")
	val firstAirDate: String? = null,

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("original_language")
	val originalLanguage: String? = null,

	@field:SerializedName("genre_ids")
	val genreIds: List<Int>? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

	@field:SerializedName("origin_country")
	val originCountry: List<String>? =null,

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@field:SerializedName("original_name")
	val originalName: String? = null,

	@field:SerializedName("popularity")
	val popularity: Double? =null,

	@field:SerializedName("vote_average")
	val voteAverage: Double = 0.0,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("vote_count")
	val voteCount: Int? =null
)
