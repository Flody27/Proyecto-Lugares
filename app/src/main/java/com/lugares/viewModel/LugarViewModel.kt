package com.lugares.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.lugares.data.LugarDao
import com.lugares.model.Lugar
import com.lugares.repository.LugarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LugarViewModel(application: Application) : AndroidViewModel(application)  {

    private val lugarRepository: LugarRepository = LugarRepository(LugarDao())
    val getLugares: MutableLiveData<List<Lugar>> = lugarRepository.getLugares

    fun saveLugar(lugar: Lugar){
        viewModelScope.launch(Dispatchers.IO) {
            lugarRepository.saveLugar(lugar) }
    }

    fun deleteLugar(lugar: Lugar){
        viewModelScope.launch(Dispatchers.IO) {
            lugarRepository.deleteLugar(lugar) }
    }

}