package com.jhoander.therickandmorty.data.remote

import com.jhoander.therickandmorty.domain.model.Cartoon
import io.reactivex.Observable
import retrofit2.http.GET

interface CartoonApi {

    @GET("character")
    fun getCharacters(): Observable<Cartoon>
}