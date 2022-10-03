package com.lugares.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.lugares.data.LugarDataBase
import com.lugares.model.Lugar
import com.lugares.repository.LugarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LugarViewModel(application: Application) : AndroidViewModel(application) {

    val getAllData: LiveData<List<Lugar>>

    private val respository: LugarRepository

    init {
        val lugarDao = LugarDataBase.getDataBase(application).lugarDao()
        respository = LugarRepository(lugarDao)
        getAllData = respository.getAllData
    }

    fun addLugar(lugar: Lugar) {
        viewModelScope.launch(Dispatchers.IO) {
            respository.addLugar(lugar) }
    }

    fun updateLugar(lugar: Lugar) {
        viewModelScope.launch(Dispatchers.IO) {
            respository.updateLugar(lugar) }
    }

    fun deleteLugar(lugar: Lugar) {
        viewModelScope.launch(Dispatchers.IO) {
            respository.deleteLugar(lugar) }
    }

}