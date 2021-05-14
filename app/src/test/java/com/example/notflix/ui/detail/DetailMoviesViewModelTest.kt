package com.example.notflix.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.notflix.data.MoviesRepositories
import com.example.notflix.data.local.entity.EpisodesEntity
import com.example.notflix.data.local.entity.MoviesEntity
import com.example.notflix.data.local.entity.TvShowEntity
import com.example.notflix.utils.DataMovies
import com.example.notflix.values.ResourceData
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
    private lateinit var trendingobserver : Observer<ResourceData<PagedList<MoviesEntity>>>

    @Mock
    private lateinit var pagedList : PagedList<MoviesEntity>

    @Mock
    private lateinit var episodesObserver : Observer<List<EpisodesEntity>>

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
    fun testShowTrendingMovies(){
        val dummyMovies = ResourceData.success(pagedList)
        Mockito.`when`(dummyMovies.data?.size).thenReturn(20)
        val actualMovies = MutableLiveData<ResourceData<PagedList<MoviesEntity>>>()
        actualMovies.value = dummyMovies

        Mockito.`when`(moviesRepositories.getAllTrendingMovies()).thenReturn(actualMovies)
        val moviesEntity = viewModel.showTrendingMovies().value?.data

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

        viewModel.showEpisodes().observeForever(episodesObserver)
        verify(episodesObserver).onChanged(dummyEps)

    }

//    @Test
//    fun testIsFavoriteMovie(){
//        val dummyMovie = DataMovies.generateDataMovies()
//        val dummyEntity = MutableLiveData<ResourceData<MoviesEntity>>()
//         dummyEntity.value = dummyMovie
//        Mockito.`when`(state?.let {
//            moviesRepositories.insertFavMovie(DataMovies.generateDataMovies()[0],
//                it
//            )
//        }).thenReturn(dummyEntity)
//    }

    @Test
    fun testIsFavoriteTv(){

    }
}