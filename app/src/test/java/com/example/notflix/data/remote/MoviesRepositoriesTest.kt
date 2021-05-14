package com.example.notflix.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.notflix.data.local.LocalDataSource
import com.example.notflix.data.local.entity.MoviesEntity
import com.example.notflix.data.local.entity.TvShowEntity
import com.example.notflix.utils.*
import com.example.notflix.values.ResourceData
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class MoviesRepositoriesTest : TestCase() {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutor = mock(AppExecutor::class.java)
    private val fakeMoviesRepositories = FakeMoviesRepositories(remote,local,appExecutor)

    private val moviesResponse = DataMovies.generateRemoteDummyMovies()
    private val movieId = moviesResponse[0].id

    private val tvResponse = DataMovies.generateRemoteDummyTv()
    private val tvshowId = tvResponse[0].id

    private val detailmoviesResponse = DataMovies.generateDetailMovies()
    private val detailtvResponse = DataMovies.generateDetailTvShow()


    @Test
    fun testGetAllTrendingMovies() {
        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        `when`(local.getAllMovies()).thenReturn(dataSource)
        fakeMoviesRepositories.getAllTrendingMovies()

        val moviesEntity = ResourceData.success(PagedListUtil.mockPagedList(DataMovies.generateDataMovies()))
        verify(local).getAllMovies()
        assertNotNull(moviesEntity.data)
        assertEquals(moviesResponse.size,moviesEntity.data?.size)
    }

    @Test
    fun testGetAllPopularTvShow() {
        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,TvShowEntity>
        `when`(local.getAllTvShow()).thenReturn(dataSource)
        fakeMoviesRepositories.getAllPopularTvShow()

        val tvShowEntity = ResourceData.success(PagedListUtil.mockPagedList(DataMovies.generateDataTvShow()))
        verify(local).getAllTvShow()
        assertNotNull(tvShowEntity.data)
        assertEquals(tvResponse.size,tvShowEntity.data?.size)
    }

    @Test
    fun testGetDetailMovie() {
        val dummyEntity = MutableLiveData<MoviesEntity>()
        dummyEntity.value = DataMovies.generateDataMovies()[0]

        `when`(local.getSelectedMovie(movieId)).thenReturn(dummyEntity)
        val detailMovie = LiveDataTest.getValue(fakeMoviesRepositories.getDetailMovie(movieId))
        verify(local).getSelectedMovie(movieId)
        assertNotNull(detailMovie.data)
        assertNotNull(detailMovie.data?.id_movies)
        assertEquals(detailmoviesResponse.id,detailMovie.data?.id_movies)
    }

    @Test
    fun testGetDetailTv() {
        val dummyEntity = MutableLiveData<TvShowEntity>()
        dummyEntity.value = DataMovies.generateDataTvShow()[0]

        `when`(local.getSelectedTvShow(tvshowId)).thenReturn(dummyEntity)
        val detailTvshow = LiveDataTest.getValue(fakeMoviesRepositories.getDetailTv(tvshowId))
        verify(local).getSelectedTvShow(tvshowId)
        assertNotNull(detailTvshow.data)
        assertNotNull(detailTvshow.data?.id_tvshow)
        assertEquals(detailtvResponse.id,detailTvshow.data?.id_tvshow)
    }

    @Test
    fun testInsertFavMovie(){
        val dummyEntity = MutableLiveData<MoviesEntity>()
        val state = dummyEntity.value?.favorite
        runBlocking {
            if (state != null) {
                fakeMoviesRepositories.insertFavMovie(DataMovies.generateDataMovies()[0],state)
            }
            if (state != null) {
                verify(local).favoriteMovie(DataMovies.generateDataMovies()[0],state)
            }

        }
    }

    @Test
    fun testInsertFavTv(){
        val dummyEntity = MutableLiveData<TvShowEntity>()
        val state = dummyEntity.value?.favorite

        runBlocking {
            if (state != null) {
                fakeMoviesRepositories.insertFavTv(DataMovies.generateDataTvShow()[0],state)
            }
            if (state != null) {
                verify(local).favoriteTv(DataMovies.generateDataTvShow()[0],state)
            }
        }
    }

    @Test
    fun testGetAllFavMovie(){
        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,MoviesEntity>
        `when`(local.getAllFavMovie()).thenReturn(dataSource)
        fakeMoviesRepositories.getAllFavMovie()

        val moviesEntity = ResourceData.success(PagedListUtil.mockPagedList(DataMovies.generateDataMovies()))
        verify(local).getAllFavMovie()
        assertNotNull(moviesEntity.data)
        assertEquals(moviesResponse.size,moviesEntity.data?.size)
    }

    @Test
    fun testGetAllFavTv(){
        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,TvShowEntity>
        `when`(local.getAllFavTv()).thenReturn(dataSource)
        fakeMoviesRepositories.getAllFavTv()

        val tvShowEntiry = ResourceData.success(PagedListUtil.mockPagedList(DataMovies.generateDataTvShow()))
        verify(local).getAllFavTv()
        assertNotNull(tvShowEntiry.data)
        assertEquals(moviesResponse.size,tvShowEntiry.data?.size)
    }
}