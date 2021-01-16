package com.jhoander.therickandmorty.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jhoander.therickandmorty.presentation.viewmodel.CharacterListViewModel
import com.jhoander.therickandmorty.utils.base.ViewModelFactory
import com.jhoander.therickandmorty.utils.base.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CharacterListActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(CharacterListViewModel::class)
    abstract fun bindCompaniesViewModel(viewModel: CharacterListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}