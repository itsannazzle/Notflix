package com.example.notflix.ui.tvshow

import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest : TestCase() {
    private lateinit var viewModel: TvShowViewModel
    @Before
    override fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun testGetTvShow() {
        assertNotNull(viewModel.getTvShow())
        assertEquals(5,viewModel.getTvShow().size)
    }
}