package com.example.notflix.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.notflix.utils.DataMovies
import com.example.notflix.utils.LiveDataTest
import com.nhaarman.mockitokotlin2.*
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesRepositoriesTest : TestCase() {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val fakeMoviesRepositories = FakeMoviesRepositories(remote)

    private val moviesResponse = DataMovies.generateRemoteDummyMovies()
    private val movieId = moviesResponse[0].id

    private val detailmoviesResponse = DataMovies.generateDetailMovies()
    private val detailtvResponse = DataMovies.generateDetailTvShow()



    private val tvResponse = DataMovies.generateRemoteDummyTv()
    private val tvshowId = tvResponse[0].id



    @Before
    fun setup() {

    }

    @Test
    fun testGetAllTrendingMovies() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[0] as RemoteDataSource.TrendingCallback)
                    .onTrendingMovies(moviesResponse)
            null
        }.`when`(remote).getTrendingMovies(any())
        val moviesEntity = LiveDataTest.getValue(fakeMoviesRepositories.getAllTrendingMovies())
        verify(remote).getTrendingMovies(any())
        assertNotNull(moviesEntity)
        assertEquals(moviesResponse.size.toLong(),moviesEntity.size.toLong())
    }

    @Test
    fun testGetAllPopularTvShow() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[0] as RemoteDataSource.TvShowCallback)
                    .onPopularTvShow(tvResponse)
            null
        }.`when`(remote).getPopularTvShow(any())
        val tvShowEntity = LiveDataTest.getValue(fakeMoviesRepositories.getAllPopularTvShow())
        verify(remote).getPopularTvShow(any())
        assertNotNull(tvShowEntity)
        assertEquals(tvResponse.size,tvShowEntity.size)
    }

    @Test
    fun testGetDetailMovie() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[1] as RemoteDataSource.DetailMovieCallback)
                    .onDetailMovies(detailmoviesResponse)
            null
        }.`when`(remote).getDetailMovie(eq(movieId), any())
        val detailMovie = LiveDataTest.getValue(fakeMoviesRepositories.getDetailMovie(movieId))
        verify(remote).getDetailMovie(eq(movieId), any())
        assertNotNull(detailMovie)
        assertEquals(detailmoviesResponse.id,detailMovie.id_movies)
    }

    @Test
    fun testGetDetailTv() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[1] as RemoteDataSource.DetailTvSHowCallback)
                    .onDetailTvShow(detailtvResponse)
            null
        }.`when`(remote).getDetailTvShow(eq(tvshowId), any())
        val detailTvshow = LiveDataTest.getValue(fakeMoviesRepositories.getDetailTv(tvshowId))
        verify(remote).getDetailTvShow(eq(tvshowId), any())
        assertNotNull(detailTvshow)
        assertEquals(detailtvResponse.id,detailTvshow.id_tvshow)
    }
}