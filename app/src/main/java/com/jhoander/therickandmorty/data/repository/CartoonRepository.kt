package com.jhoander.therickandmorty.data.repository

import com.jhoander.therickandmorty.domain.model.Cartoon
import io.reactivex.Observable

interface CartoonRepository {
    fun getCharacters(): Observable<Cartoon>
}