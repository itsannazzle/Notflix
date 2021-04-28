package com.example.notflix.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.notflix.data.remote.MoviesRepositories
import com.example.notflix.entity.EpisodesEntity
import com.example.notflix.entity.MoviesEntity
import com.example.notflix.entity.TvShowEntity
import com.example.notflix.utils.DataMovies
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
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
    private lateinit var trendingobserver : Observer<List<MoviesEntity>>

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
        assertEquals(dummyTvShow.id_tvshow, moviesList.id_tvshow)
        assertEquals(dummyTvShow.poster, moviesList.poster)
        assertEquals(dummyTvShow.country, moviesList.country)
        assertEquals(dummyTvShow.duration, moviesList.duration)
        assertEquals(dummyTvShow.genre, moviesList.genre)
        assertEquals(dummyTvShow.title, moviesList.title)
        assertEquals(dummyTvShow.overview,moviesList.overview)
        assertEquals(dummyTvShow.rating,moviesList.rating)

        viewModel.showDetailTvShow().observeForever(tvshowObserver)
        verify(tvshowObserver).onChanged(dummyTvShow)
    }

    @Test
    fun testShowTrendingMovies(){
        val dummyMovies = DataMovies.generateDataMovies()
        val actualMovies = MutableLiveData<List<MoviesEntity>>()
        actualMovies.value = dummyMovies

        Mockito.`when`(moviesRepositories.getAllTrendingMovies()).thenReturn(actualMovies)
        val moviesEntity = viewModel.showTrendingMovies().value

        verify(moviesRepositories).getAllTrendingMovies()
        assertNotNull(moviesEntity)
        assertEquals(20,moviesEntity?.size)

        viewModel.showTrendingMovies().observeForever(trendingobserver)
        verify(trendingobserver).onChanged(dummyMovies)
    }

    @Test
    fun testShowEpisodes(){
        val dummyEps = DataMovies.generateEpisodes()
        val actualEps = MutableLiveData<List<EpisodesEntity>>()
        actualEps.value = dummyEps
        Mockito.`when`(moviesRepositories.getEpisodes()).thenReturn(actualEps)
        val epsEntity = viewModel.showEpisodes().value

        verify(moviesRepositories).getEpisodes()
        assertNotNull(epsEntity)
        assertEquals(5, epsEntity?.size)

    }

}