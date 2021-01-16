package com.jhoander.therickandmorty.domain.usecase

import com.jhoander.therickandmorty.data.repository.CartoonRepository
import com.jhoander.therickandmorty.domain.model.Cartoon
import com.jhoander.therickandmorty.utils.base.UseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetCharacterListUseCase @Inject constructor(private val cartoonRepository: CartoonRepository) :
    UseCase<Cartoon>() {
    override fun createObservableUseCase(): Observable<Cartoon> {
        return cartoonRepository.getCharacters()
    }
}