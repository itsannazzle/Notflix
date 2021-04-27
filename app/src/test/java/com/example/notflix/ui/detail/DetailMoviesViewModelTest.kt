package com.example.notflix.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.notflix.data.remote.MoviesRepositories
import com.example.notflix.entity.MoviesEntity
import com.example.notflix.entity.TvShowEntity
import com.example.notflix.utils.DataMovies
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class DetailMoviesViewModelTest : TestCase() {
    private lateinit var viewModel: DetailMoviesViewModel
    private val dummyMovies = DataMovies.generateDataMovies()[0]
    private val dummyTvShow = DataMovies.generateDataTvShow()[0]
    private val moviesId = dummyMovies.id_movies
    private val tvshowId = dummyTvShow.id_tvshow

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesObserver: Observer<MoviesEntity>

    @Mock
    private lateinit var tvshowObserver: Observer<TvShowEntity>

    @Mock
    private lateinit var moviesRepositories: MoviesRepositories

    @Before
    fun setup() {
        viewModel = DetailMoviesViewModel(moviesRepositories)

        viewModel.getSelectedMovie(moviesId)

        viewModel.getSelectedTvShow(tvshowId)
    }

    @Test
    fun testShowDetailMovie() {
        val movie =  MutableLiveData<MoviesEntity>()
        movie.value = dummyMovies
        Mockito.`when`(moviesRepositories.getDetailMovie(moviesId)).thenReturn(movie)
        val moviesList = viewModel.showDetailMovie().value as MoviesEntity
        verify(moviesRepositories).getDetailMovie(moviesId)
        assertNotNull(moviesList)
        assertEquals(dummyMovies.id_movies, moviesList.id_movies)
        assertEquals(dummyMovies.poster, moviesList.poster)
        assertEquals(dummyMovies.country, moviesList.country)
        assertEquals(dummyMovies.duration, moviesList.duration)
        assertEquals(dummyMovies.genre, moviesList.genre)
        assertEquals(dummyMovies.title, moviesList.title)
        assertEquals(dummyMovies.overview,moviesList.overview)
        assertEquals(dummyMovies.rating,moviesList.rating)

        viewModel.showDetailMovie().observeForever(moviesObserver)
        verify(moviesObserver).onChanged(dummyMovies)
    }

    @Test
    fun testShowDetailTvShow() {
        val tvshow =  MutableLiveData<TvShowEntity>()
        tvshow.value = dummyTvShow
        Mockito.`when`(moviesRepositories.getDetailTv(tvshowId)).thenReturn(tvshow)
        val moviesList = viewModel.showDetailTvShow().value as TvShowEntity
        verify(moviesRepositories).getDetailTv(tvshowId)
        assertNotNull(moviesList)
        assertEquals(dummyMovies.id_movies, moviesList.id_tvshow)
        assertEquals(dummyMovies.poster, moviesList.poster)
        assertEquals(dummyMovies.country, moviesList.country)
        assertEquals(dummyMovies.duration, moviesList.duration)
        assertEquals(dummyMovies.genre, moviesList.genre)
        assertEquals(dummyMovies.title, moviesList.title)
        assertEquals(dummyMovies.overview,moviesList.overview)
        assertEquals(dummyMovies.rating,moviesList.rating)

        viewModel.showDetailTvShow().observeForever(tvshowObserver)
        verify(tvshowObserver).onChanged(dummyTvShow)
    }

//    @Test
//    fun testShowEpisodes() {
//        assertNotNull(viewModel.showEpisodes())
//        assertEquals(5, viewModel.showEpisodes().size)
//    }
}