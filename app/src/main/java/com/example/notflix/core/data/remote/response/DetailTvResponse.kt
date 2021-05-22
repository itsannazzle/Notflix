package com.example.notflix.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetailTvResponse(


	@field:SerializedName("number_of_episodes")
	val numberOfEpisodes: Int,

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("genres")
	val genres: List<TVGenresItem>,

	@field:SerializedName("production_countries")
	val productionCountries: List<TVProductionCountriesItem>,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("number_of_seasons")
	val numberOfSeasons: Int,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("episode_run_time")
	val episodeRunTime: List<Int>,

)


data class TVGenresItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int?=0
)

data class TVProductionCountriesItem(

	@field:SerializedName("iso_3166_1")
	val iso31661: String?=null,

	@field:SerializedName("name")
	val name: String
)


