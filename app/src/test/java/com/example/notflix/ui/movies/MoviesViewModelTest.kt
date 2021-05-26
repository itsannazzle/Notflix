package com.example.notflix.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nextint.core.data.MoviesRepositories
import com.example.notflix.core.data.local.entity.MoviesEntity
import com.example.notflix.core.values.ResourceData
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
class MoviesViewModelTest : TestCase() {
    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepositories: _root_ide_package_.com.nextint.core.data.MoviesRepositories
    
    @Mock
    private lateinit var observer : Observer<ResourceData<PagedList<MoviesEntity>>>

    @Mock
    private lateinit var pagedList : PagedList<MoviesEntity>


    @Before
    public override fun setUp() {
        viewModel = MoviesViewModel(moviesRepositories)
    }


    @Test
    fun testShowTrendingMovies() {
        val dummyMovies = ResourceData.success(pagedList)
        Mockito.`when`(dummyMovies.data?.size).thenReturn(20)
        val actualMovies = MutableLiveData<ResourceData<PagedList<MoviesEntity>>>()
        actualMovies.value = dummyMovies

        Mockito.`when`(moviesRepositories.getAllTrendingMovies()).thenReturn(actualMovies)
        val moviesEntity = viewModel.showTrendingMovies().value?.data

        verify(moviesRepositories).getAllTrendingMovies()
        assertNotNull(moviesEntity)
        assertEquals(20,moviesEntity?.size)

        viewModel.showTrendingMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}