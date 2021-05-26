package com.example.notflix.di

import com.example.notflix.ui.detail.DetailMoviesViewModel
import com.example.notflix.ui.favorite.FavoriteViewModel
import com.example.notflix.ui.movies.MoviesViewModel
import com.example.notflix.ui.tvshow.TvShowViewModel
import com.nextint.core.domain.usecase.NotflixInteractor
import com.nextint.core.domain.usecase.NotflixUsecase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val usecaseModule = module {
    factory<NotflixUsecase> { NotflixInteractor(get()) }
}

val viewModelModule = module {
    viewModel { DetailMoviesViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { MoviesViewModel(get()) }
    viewModel { TvShowViewModel(get()) }


}