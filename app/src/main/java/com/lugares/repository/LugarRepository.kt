package com.lugares.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lugares.data.LugarDao
import com.lugares.model.Lugar

class LugarRepository(private val lugarDao: LugarDao) {

    fun saveLugar(lugar: Lugar){
        lugarDao.saveLugar(lugar)

    }
    fun deleteLugar(lugar: Lugar){
        lugarDao.deleteLugar(lugar)
    }

    val getLugares : MutableLiveData<List<Lugar>> = lugarDao.getLugares()

}