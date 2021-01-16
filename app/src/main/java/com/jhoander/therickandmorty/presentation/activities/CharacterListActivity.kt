package com.jhoander.therickandmorty.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhoander.therickandmorty.R
import com.jhoander.therickandmorty.di.component.DaggerCharacterListActivityComponent
import com.jhoander.therickandmorty.domain.model.Cartoon
import com.jhoander.therickandmorty.presentation.adapter.CharacterListAdapter
import com.jhoander.therickandmorty.presentation.viewmodel.CharacterListViewModel
import com.jhoander.therickandmorty.utils.Failure
import com.jhoander.therickandmorty.utils.ResourceState
import com.jhoander.therickandmorty.utils.base.ViewModelFactory
import com.jhoander.therickandmorty.utils.base.getViewModel
import com.jhoander.therickandmorty.utils.extension.showMessage
import com.jhoander.therickandmorty.utils.extension.showProgress
import kotlinx.android.synthetic.main.activity_character_list.*
import javax.inject.Inject

class CharacterListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: CharacterListViewModel

    lateinit var adapter: CharacterListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)
        injectDependence()
        initViewModel()
        viewModel.getCharacterList()
    }

    private fun injectDependence() {
        DaggerCharacterListActivityComponent.builder().build().inject(this)

    }

    private fun initViewModel() {
        viewModel = getViewModel(viewModelFactory)
        viewModel.liveData.observe(this, Observer {
            it?.also {
                handleCharacterList(it.status, it.data, it.failure)
            }
        })
    }

    private fun displayCharacters(cartoon: Cartoon?) {
        adapter = CharacterListAdapter()
        newsRv.layoutManager = LinearLayoutManager(this)
        newsRv.layoutManager = GridLayoutManager(this, 1)
        newsRv.adapter = adapter

        cartoon?.let {
            adapter.setList(it.results)

        }
    }

    private fun handleCharacterList(status: ResourceState, data: Cartoon?, failure: Failure?) {
        when (status) {
            ResourceState.LOADING -> {
                pbCharacter.showProgress(true, this)
            }
            ResourceState.SUCCESS -> {
                pbCharacter.showProgress(false, this)
                data?.let {
                    displayCharacters(data)
                }
            }
            ResourceState.ERROR -> {
                pbCharacter.showProgress(false, this)
                when (failure) {
                    Failure.NetworkConnection -> {

                    }
                    Failure.ServerError -> {
                        pbCharacter.showProgress(false, this)
                        showMessage((failure as Failure.Error).errorMessage, this)
                    }
                }
            }
            else -> {
            }
        }

    }

}
