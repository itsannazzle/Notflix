package com.example.notflix.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.notflix.data.remote.MoviesRepositories
import com.example.notflix.entity.MoviesEntity
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
class MoviesViewModelTest : TestCase() {
    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepositories: MoviesRepositories
    
    @Mock
    private lateinit var observer : Observer<List<MoviesEntity>>


    @Before
    public override fun setUp() {
        viewModel = MoviesViewModel(moviesRepositories)
    }


    @Test
    fun testGetMovies() {
        val dummyMovies = DataMovies.generateDataMovies()
        val actualMovies = MutableLiveData<List<MoviesEntity>>()
        actualMovies.value = dummyMovies

        Mockito.`when`(moviesRepositories.getAllTrendingMovies()).thenReturn(actualMovies)
        val moviesEntity = viewModel.showTrendingMovies().value

        verify(moviesRepositories).getAllTrendingMovies()
        assertNotNull(moviesEntity)
        assertEquals(20,moviesEntity?.size)

        viewModel.showTrendingMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}