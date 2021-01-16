package com.jhoander.therickandmorty.di.component

import com.jhoander.therickandmorty.di.module.CartoonRepositoryModule
import com.jhoander.therickandmorty.di.module.CharacterListActivityModule
import com.jhoander.therickandmorty.presentation.activities.CharacterListActivity
import com.jhoander.therickandmorty.utils.base.ActivityComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CharacterListActivityModule::class, CartoonRepositoryModule::class])
interface CharacterListActivityComponent : ActivityComponent<CharacterListActivity> {
}