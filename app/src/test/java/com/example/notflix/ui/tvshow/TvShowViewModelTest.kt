package com.example.notflix.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.notflix.data.MoviesRepositories
import com.example.notflix.data.local.entity.TvShowEntity
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
class TvShowViewModelTest : TestCase() {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepositories: MoviesRepositories

    @Mock
    private lateinit var observer : Observer<ResourceData<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedList : PagedList<TvShowEntity>

    @Before
    public override fun setUp() {
        viewModel = TvShowViewModel(moviesRepositories)
    }


    @Test
    fun testGetMovies() {
        val dummyMovies = ResourceData.success(pagedList)
        Mockito.`when`(dummyMovies.data?.size).thenReturn(20)
        val actualMovies = MutableLiveData<ResourceData<PagedList<TvShowEntity>>>()
        actualMovies.value = dummyMovies

        Mockito.`when`(moviesRepositories.getAllPopularTvShow()).thenReturn(actualMovies)
        val moviesEntity = viewModel.showTvShow().value?.data

        verify(moviesRepositories).getAllPopularTvShow()
        assertNotNull(moviesEntity)
        assertEquals(20,moviesEntity?.size)

        viewModel.showTvShow().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}