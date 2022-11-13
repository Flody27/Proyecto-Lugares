package com.lugares.ui.lugar

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.lugares.R
import com.lugares.databinding.FragmentAddLugarBinding
import com.lugares.model.Lugar
import com.lugares.viewModel.LugarViewModel

class AddLugarFragment : Fragment() {

    //El objeto para interactuar finalemt
    private lateinit var lugarViewModel: LugarViewModel

    private var _binding: FragmentAddLugarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         lugarViewModel=
            ViewModelProvider(this).get(LugarViewModel::class.java)

        _binding = FragmentAddLugarBinding.inflate(inflater, container, false)


        binding.btAdd.setOnClickListener { addLugar() }

        activaGPS()

        return binding.root
    }

    private fun activaGPS() {
      if(requireActivity().checkSelfPermission(Manifest.permission.
          ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
          requireActivity().checkSelfPermission(Manifest.permission.
          ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
          // Al  acceder aca  es por que no se posee  permisos y se consultan
          requireActivity().requestPermissions(
              arrayOf(Manifest.permission.
              ACCESS_COARSE_LOCATION,Manifest.permission.
              ACCESS_FINE_LOCATION),105)

      }else{
          //Se procede a obtener la localizacion del gps
          val ubicacion: FusedLocationProviderClient =
              LocationServices.getFusedLocationProviderClient(requireContext())
          ubicacion.lastLocation.addOnSuccessListener {
              location: Location? ->
              if(location != null){
                  binding.tvLatitud.text = "${location.latitude}"
                  binding.tvLongitud.text = "${location.longitude}"
                  binding.tvAltura.text = "${location.altitude}"
              }else{
                  binding.tvLatitud.text = "0.0"
                  binding.tvLongitud.text = "0.0"
                  binding.tvAltura.text = "0.0"
              }
          }
      }
    }

    private fun addLugar()  
    {
        val nombre=binding.etNombre.text.toString() // Obtiene el texto de lo que el usuario escribio
        if (nombre.isNotEmpty())
        {
            val correo = binding.etCorreo.text.toString()
            val telefono = binding.etTelefono.text.toString()
            val web = binding.etWeb.text.toString()
            val latitud = binding.tvLatitud.text.toString().toDouble()
            val longitud = binding.tvLongitud.text.toString().toDouble()
            val altura = binding.tvAltura.text.toString().toDouble()
            val lugar = Lugar("",nombre,correo,telefono,web,latitud,longitud,altura,"","")
                //Se procede a registrar el nuevo lugar
            lugarViewModel.saveLugar(lugar)
            Toast.makeText(requireContext(),getString(R.string.msg_lugar_added),Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addLugarFragment_to_nav_lugar)

        } else
        {// No se puede registar el lugar porque falta informacion
            Toast.makeText(requireContext(),getString(R.string.msg_data),Toast.LENGTH_SHORT).show()

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}