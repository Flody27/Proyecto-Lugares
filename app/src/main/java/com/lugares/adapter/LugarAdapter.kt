package com.lugares.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lugares.databinding.LugarFilaBinding
import com.lugares.model.Lugar
import com.lugares.ui.lugar.LugarFragmentDirections

class LugarAdapter : RecyclerView.Adapter<LugarAdapter.LugarViewHolder>()
{

    //Clase interna que se encarga finalmente de dibujar la informacion
    inner class LugarViewHolder(private val itemBinding: LugarFilaBinding)
        :RecyclerView.ViewHolder(itemBinding.root)
    {
            fun dibuja(lugar: Lugar)
            {
                itemBinding.tvNombre.text = lugar.nombre
                itemBinding.tvCorreo.text = lugar.correo
                itemBinding.tvTelefono.text = lugar.telefono

                Glide.with(itemBinding.root.context)
                    .load(lugar.rutaImagen)
                    .circleCrop()
                    .into(itemBinding.imagen)

                itemBinding.vistaFila.setOnClickListener{
                    // Creo una accion para navegar a updatelugar pasando un argumento tipo lugar
                    val action = LugarFragmentDirections
                        .actionNavLugarToUpdateLugarFragment(lugar)
                    itemView.findNavController().navigate(action)
                }
            }

    }

    //La lista donde estan los objetos Lugar a dibujarse
    private var listaLugares = emptyList<Lugar>()



    //Esta funcion crea cajitas para cada lugar en memoria
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LugarViewHolder {

        val itemBinding = LugarFilaBinding.inflate(
            LayoutInflater.from(parent.context),
        parent,
        false)
        return LugarViewHolder(itemBinding)
    }

    //Esta funcion toma un lugar y lo envia a dibujar
    override fun onBindViewHolder(holder: LugarViewHolder, position: Int) {

        val lugar = listaLugares[position]
        holder.dibuja(lugar)
    }

   // Esta funcion devuelve la cantidad de cajitas a dibujar
    override fun getItemCount(): Int {
        return listaLugares.size
    }

    fun setListaLugares(lugares: List<Lugar>)
    {
        this.listaLugares = lugares
        notifyDataSetChanged()
    }

}