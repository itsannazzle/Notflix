package com.example.notflix.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.notflix.data.remote.MoviesRepositories
import com.example.notflix.data.local.entity.TvShowEntity
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
class TvShowViewModelTest : TestCase() {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepositories: MoviesRepositories

    @Mock
    private lateinit var observer : Observer<List<TvShowEntity>>


    @Before
    public override fun setUp() {
        viewModel = TvShowViewModel(moviesRepositories)
    }


    @Test
    fun testGetMovies() {
        val dummyMovies = DataMovies.generateDataTvShow()
        val actualMovies = MutableLiveData<List<TvShowEntity>>()
        actualMovies.value = dummyMovies

        Mockito.`when`(moviesRepositories.getAllPopularTvShow()).thenReturn(actualMovies)
        val moviesEntity = viewModel.showTvShow().value

        verify(moviesRepositories).getAllPopularTvShow()
        assertNotNull(moviesEntity)
        assertEquals(20,moviesEntity?.size)

        viewModel.showTvShow().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}