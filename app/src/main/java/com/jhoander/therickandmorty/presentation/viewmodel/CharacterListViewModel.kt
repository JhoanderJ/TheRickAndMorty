package com.jhoander.therickandmorty.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jhoander.therickandmorty.domain.model.Cartoon
import com.jhoander.therickandmorty.domain.usecase.GetCharacterListUseCase
import com.jhoander.therickandmorty.utils.Failure
import com.jhoander.therickandmorty.utils.Resource
import com.jhoander.therickandmorty.utils.ResourceState
import com.jhoander.therickandmorty.utils.base.UseCaseObserver
import com.jhoander.therickandmorty.utils.post
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(private val getCharacterListUseCase: GetCharacterListUseCase) :
    ViewModel() {

    var liveData: MutableLiveData<Resource<Cartoon>> = MutableLiveData()

    fun getCharacterList() {
        liveData.post(status = ResourceState.LOADING)
        getCharacterListUseCase.execute(object : UseCaseObserver<Cartoon>() {
            override fun onNext(value: Cartoon) {
                liveData.post(status = ResourceState.SUCCESS, data = value)
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                liveData.post(
                    status = ResourceState.ERROR, failure = Failure.Error(
                        "ha ocurrido un error inesperado intente nuevamente"
                    )
                )
            }
        })

    }

    override fun onCleared() {
        getCharacterListUseCase.dispose()
    }

}