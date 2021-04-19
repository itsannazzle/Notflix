package com.example.notflix.ui.detail

import com.example.notflix.utils.DataMovies
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class DetailMoviesViewModelTest : TestCase() {
    private lateinit var viewModel: DetailMoviesViewModel
    private val dummyMovies = DataMovies.generateDataMovies()[0]
    private val dummyTvShow = DataMovies.generateDataTvShow()[0]
    private val moviesId = dummyMovies.id_movies
    private val tvshowId = dummyTvShow.id_tvshow

    @Before
    public override fun setUp() {
        viewModel = DetailMoviesViewModel()

        viewModel.getSelectedMovie(moviesId)

        viewModel.getSelectedTvShow(tvshowId)
    }

    @Test
    fun testShowDetailMovie() {
        viewModel.getSelectedMovie(dummyMovies.id_movies)
        val moviesList = viewModel.showDetailMovie()
        assertNotNull(moviesList)
        assertEquals(dummyMovies.id_movies, moviesList.id_movies)
        assertEquals(dummyMovies.poster, moviesList.poster)
        assertEquals(dummyMovies.country, moviesList.country)
        assertEquals(dummyMovies.duration, moviesList.duration)
        assertEquals(dummyMovies.genre, moviesList.genre)
        assertEquals(dummyMovies.title, moviesList.title)
        assertEquals(dummyMovies.overview,moviesList.overview)
        assertEquals(dummyMovies.rating,moviesList.rating)

    }

    @Test
    fun testShowDetailTvShow() {
        viewModel.getSelectedMovie(dummyTvShow.id_tvshow)
        val tvShowList = viewModel.showDetailTvShow()
        assertNotNull(tvShowList)
        assertEquals(dummyTvShow.id_tvshow, tvShowList.id_tvshow)
        assertEquals(dummyTvShow.poster, tvShowList.poster)
        assertEquals(dummyTvShow.country, tvShowList.country)
        assertEquals(dummyTvShow.duration, tvShowList.duration)
        assertEquals(dummyTvShow.genre, tvShowList.genre)
        assertEquals(dummyTvShow.title, tvShowList.title)
        assertEquals(dummyTvShow.overview,tvShowList.overview)
        assertEquals(dummyTvShow.rating,tvShowList.rating)
    }

    @Test
    fun testShowEpisodes() {
        assertNotNull(viewModel.showEpisodes())
        assertEquals(5, viewModel.showEpisodes().size)
    }
}