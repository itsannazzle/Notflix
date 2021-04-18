package com.example.notflix.ui.movies

import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

class MoviesViewModelTest : TestCase() {
    private lateinit var viewModel: MoviesViewModel

    @Before
    public override fun setUp() {
        viewModel = MoviesViewModel()
    }

    @Test
    fun testGetMovies() {
        assertNotNull(viewModel.getMovies())
        assertEquals(5,viewModel.getMovies().size)
    }
}