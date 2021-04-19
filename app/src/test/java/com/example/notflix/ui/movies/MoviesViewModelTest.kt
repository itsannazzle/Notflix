package com.example.notflix.ui.movies

import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class MoviesViewModelTest : TestCase() {
    private lateinit var viewModel: MoviesViewModel

    @Before
    public override fun setUp() {
        viewModel = MoviesViewModel()
    }

    @Test
    fun testGetMovies() {
        assertNotNull(viewModel.getMovies())
        assertEquals(11,viewModel.getMovies().size)
    }
}