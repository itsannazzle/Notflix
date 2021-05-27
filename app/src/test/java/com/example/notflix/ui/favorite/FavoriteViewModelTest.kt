package com.example.notflix.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nextint.core.data.MoviesRepositories
import com.example.notflix.core.data.local.entity.MoviesEntity
import com.example.notflix.core.data.local.entity.TvShowEntity
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
class FavoriteViewModelTest : TestCase() {

    private lateinit var viewModel: com.nextint.favoritefeature.favorite.FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepositories: _root_ide_package_.com.nextint.core.data.MoviesRepositories

    @Mock
    private lateinit var observerFavMovie : Observer<PagedList<MoviesEntity>>

    @Mock
    private lateinit var observerTv : Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedListFavMovie : PagedList<MoviesEntity>

    @Mock
    private lateinit var pagedListFavTv : PagedList<TvShowEntity>

    @Before
    public override fun setUp() {
        viewModel = com.nextint.favoritefeature.favorite.FavoriteViewModel(moviesRepositories)
    }

    @Test
    fun testShowFavMovie() {
        val dummyFavoriteMovie = pagedListFavMovie
        Mockito.`when`(dummyFavoriteMovie.size).thenReturn(3)
        val favorite = MutableLiveData<PagedList<MoviesEntity>>()
        favorite.value = dummyFavoriteMovie

        Mockito.`when`(moviesRepositories.getAllFavMovie()).thenReturn(favorite)
        val favEntity = viewModel.showFavMovie().value
        print(favEntity?.size)
        verify(moviesRepositories).getAllFavMovie()
        assertNotNull(favEntity)
        assertEquals(3, favEntity?.size)

        viewModel.showFavMovie().observeForever(observerFavMovie)
        verify(observerFavMovie).onChanged(dummyFavoriteMovie)
    }

    @Test
    fun testShowFavTv() {
        val dummyFavoriteTv = pagedListFavTv
        Mockito.`when`(dummyFavoriteTv.size).thenReturn(3)
        val favorite = MutableLiveData<PagedList<TvShowEntity>>()
        favorite.value = pagedListFavTv

        Mockito.`when`(moviesRepositories.getAllFavTv()).thenReturn(favorite)
        val favEntity = viewModel.showFavTv().value

        verify(moviesRepositories).getAllFavTv()
        assertNotNull(favEntity)
        assertEquals(3, favEntity?.size)

        viewModel.showFavTv().observeForever(observerTv)
        verify(observerTv).onChanged(dummyFavoriteTv)
    }
}