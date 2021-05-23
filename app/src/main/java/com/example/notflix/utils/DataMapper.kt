package com.example.notflix.utils

import com.example.notflix.core.data.local.entity.MoviesEntity
import com.example.notflix.core.data.local.entity.TvShowEntity
import com.example.notflix.core.data.remote.response.DetailMoviesResponse
import com.example.notflix.core.data.remote.response.DetailTvResponse
import com.example.notflix.core.data.remote.response.ResultsItem
import com.example.notflix.core.data.remote.response.TVResultsItem
import com.example.notflix.core.domain.model.MoviesModel
import com.example.notflix.core.domain.model.TvShowModel

object DataMapper {
    fun mapMoviesResponseToEntity(data : List<ResultsItem>) : List<MoviesEntity>{
        val movieList = ArrayList<MoviesEntity>()
        data.map {
            with(it){
                val movie = MoviesEntity(
                    id, backdropPath, posterPath, title, genre = null, country = null, voteAverage, overview, duration = 0
                )
                movieList.add(movie)
            }
        }
        return movieList
    }

    fun mapMoviesDetailResponseToEntity(data : DetailMoviesResponse) : MoviesEntity{
        return with(data){
                val genre = ArrayList<String>()
                val prodCountry =  ArrayList<String>()
                for (gen in genres){
                    genre.add(gen.name)
                }
                for (country in productionCountries){
                    prodCountry.add(country.name)
                }
           MoviesEntity(
                    id, genre = genre.toString(), country = prodCountry.toString(), duration = runtime
                )

            }
    }

    fun mapTvShowDetailResponseToEntity(data: DetailTvResponse) : TvShowEntity{
        return with(data){
                val genre = ArrayList<String>()
                val prodCountry =  ArrayList<String>()
                for (gen in genres){
                    genre.add(gen.name)
                }
                for (country in productionCountries){
                    prodCountry.add(country.name)
                }
               TvShowEntity(
                   id, genre = genre.toString(), country = prodCountry.toString(), duration = episodeRunTime[0]
                )


        }
    }

    fun mapTvShowResponseToEntity(data: List<TVResultsItem>) : List<TvShowEntity>{
        val tvShowList = ArrayList<TvShowEntity>()
        data.map {
            with(it){
                val tvShow = TvShowEntity(
                    id, backdropPath, posterPath, name, genre = null, country = null, voteAverage,
                    overview, duration = 0
                )
                tvShowList.add(tvShow)
            }
        }
        return tvShowList
    }

    fun mapMoviesEntitiesToDomain(data: MoviesEntity) : MoviesModel{
        return with(data){
                MoviesModel(
                    id_movies, backDrop, poster, title, genre, country, rating, overview, duration, favorite
                )
            }
        }



    fun mapTvShowEntitiesToDomain(data : TvShowEntity) : TvShowModel{
        return with(data){
                TvShowModel(id_tvshow, backDrop, poster, title, genre, country, rating, overview, duration, total_eps, favorite)
            }
        }


    fun mapDomainMovieToEntity(data: MoviesModel) : MoviesEntity{
        return with(data){
            MoviesEntity(
                id_movies, backDrop, poster, title, genre, country, rating, overview, duration, favorite
            )
        }
    }

    fun mapDomainTvToEntity(data: TvShowModel) : TvShowEntity{
        return with(data){
            TvShowEntity(
                id_tvshow, backDrop, poster, title, genre, country, rating, overview, duration, total_eps, favorite
            )
        }
    }
}