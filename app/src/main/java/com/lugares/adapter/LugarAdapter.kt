package com.lugares.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lugares.databinding.LugarFilaBinding
import com.lugares.model.Lugar

class LugarAdapter : RecyclerView.Adapter<LugarAdapter.LugarViewHolder>() {

    // Una lista para alamacenar la info de los lugares
    private var listLugares = emptyList<Lugar>()

    inner class LugarViewHolder(private val itemBinding: LugarFilaBinding):
    RecyclerView.ViewHolder(itemBinding.root){

        fun bind(lugar: Lugar){
            itemBinding.tvTelefono.text = lugar.telefono
            itemBinding.tvCorreo.text = lugar.correo
            itemBinding.tvNombre.text = lugar.nombre

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LugarViewHolder {
        val itemBinding = LugarFilaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LugarViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: LugarViewHolder, position: Int) {
        val lugarActual = listLugares[position]
        holder.bind(lugarActual)
    }

    override fun getItemCount(): Int {
        return listLugares.size

    }

    fun setData(lugares: List<Lugar>){
        this.listLugares = lugares
        notifyDataSetChanged()//Provoca que se actulize la lista
    }

}