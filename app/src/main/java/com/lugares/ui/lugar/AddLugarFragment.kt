package com.lugares.ui.lugar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lugares.R
import com.lugares.databinding.FragmentAddLugarBinding
import com.lugares.databinding.FragmentLugarBinding
import com.lugares.model.Lugar
import com.lugares.viewModel.LugarViewModel

class AddLugarFragment : Fragment() {

    private var _binding: FragmentAddLugarBinding? = null
    private val binding get() = _binding!!
    private lateinit var LugarViewModel : LugarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddLugarBinding.inflate(inflater, container, false)
        LugarViewModel = ViewModelProvider(this)[LugarViewModel::class.java]
        binding.btAgregar.setOnClickListener{addLugar()}
        return binding.root;
    }

    private fun addLugar(){
        var nombre =  binding.etNombre.text.toString()
        var correo = binding.etCorreo.text.toString()
        var telefono = binding.etTelefono.text.toString()
        var web = binding.etSitioWeb.text.toString()
        if(validos(nombre,correo,telefono,web)){
            val lugar = Lugar(0,nombre,correo,telefono,web,0.0,0.0,0.0,"","")
            LugarViewModel.addLugar(lugar)
            Toast.makeText(requireContext(),getString(R.string.msg_lugar_agregado),Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(requireContext(),getString(R.string.msg_faltan_datos),Toast.LENGTH_LONG).show()
        }
        findNavController().navigate(R.id.action_addLugarFragment_to_nav_lugar)
    }

    private fun validos(nombre:String,correo:String,telefono:String,web:String):Boolean{
        return !(nombre.isNotEmpty() || correo.isNotEmpty() || telefono.isNotEmpty() || web.isNotEmpty())
    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment AddLugarFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            AddLugarFragment().apply {
//                arguments = Bundle().apply {
////                    putString(ARG_PARAM1, param1)
////                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}