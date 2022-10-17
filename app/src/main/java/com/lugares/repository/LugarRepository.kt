package com.lugares.repository

import androidx.lifecycle.LiveData
import com.lugares.data.LugarDao
import com.lugares.model.Lugar

class LugarRepository(private val lugarDao: LugarDao) {

    suspend fun saveLugar(lugar: Lugar){

        if(lugar.id==0){

            //Es un lugar nuevo...
            lugarDao.addLugar(lugar)
        }else { 
            lugarDao.updateLugar(lugar)
        }
    }
    suspend fun deleteLugar(lugar: Lugar){
            if(lugar.id != 0) {
                lugarDao.deleteLugar(lugar)
            }
    }

    val getLugares : LiveData<List<Lugar>> = lugarDao.getLugares()


}