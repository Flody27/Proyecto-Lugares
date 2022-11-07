package com.lugares.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase
import com.lugares.model.Lugar
class LugarDao {

    //Las funciones de bajo nivel para hacer un CRUD

    private val coleccion1 = "lugaresApp"
    private val coleccion2 = "misLugares"
    private val usuario =  Firebase.auth.currentUser?.email.toString()

    //Conexion de datos
    private var firestore : FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        // Inicializa la configuracion del firestore
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun saveLugar(lugar: Lugar){
        val documento: DocumentReference


        if(lugar.id.isEmpty()){
            documento = firestore
                .collection(coleccion1)
                .document(usuario).
                collection(coleccion2).
                document()
            lugar.id = documento.id
        }else{
            documento = firestore
                .collection(coleccion1).document(usuario).
                collection(coleccion2).
                document(lugar.id)
        }

        documento.set(lugar).addOnSuccessListener {
            Log.d("saveLugar","Lugar creado / Actulizado")
        }
            .addOnCanceledListener {
                Log.e("saveLugar","error creando / Actulizando lugar")
            }

    }

    fun deleteLugar(lugar: Lugar){
        if(lugar.id.isNotEmpty()){
           firestore.collection(coleccion1).document(usuario).
           collection(coleccion2).
           document(lugar.id).delete()
               .addOnSuccessListener {
                 Log.d("deleteLugar","Lugar eliminado")
               }
               .addOnCanceledListener {
                   Log.e("deleteLugar","Lugar no eliminado")
               }
        }
    }

    fun getLugares() : MutableLiveData<List<Lugar>>{
        val listaLugares = MutableLiveData<List<Lugar>>()
        firestore.collection(coleccion1).document(usuario).
        collection(coleccion2).addSnapshotListener{
                instantanea, e->
                // Se dio un error recuperando los documentos
                if(e != null){
                    return@addSnapshotListener
                }
                // Se logro recuperar los documentos
                if(instantanea != null){
                    val lista = ArrayList<Lugar>()

                    // Se recorre los documentos  por documentos
                    instantanea.documents.forEach{
                        val lugar = it.toObject(Lugar::class.java)
                        if(lugar != null){// Se logro convertir los documentos en objetos
                            lista.add(lugar)// se agregan los objetos a la lista
                        }
                    }

                    listaLugares.value = lista
                }

            }


        return listaLugares
    }

}