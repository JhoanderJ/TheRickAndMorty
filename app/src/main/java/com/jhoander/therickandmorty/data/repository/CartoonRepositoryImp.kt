package com.jhoander.therickandmorty.data.repository

import com.jhoander.therickandmorty.data.remote.CartoonApi
import com.jhoander.therickandmorty.domain.model.Cartoon
import io.reactivex.Observable

class CartoonRepositoryImp(private val api: CartoonApi) : CartoonRepository {
    override fun getCharacters(): Observable<Cartoon> {
        return api.getCharacters().map {
            if (it.results.isEmpty()) {
                throw Exception("Error")
            }
            it
        }
    }
}