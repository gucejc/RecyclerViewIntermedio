package com.example.recyclerviewintermedio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

//llamamos al xml del fragmento como parametro d entradaa la clase con la idea que se infle el xml fragment_list
class ListFragment:BaseMainActivityFragment(R.layout.fragment_list) {

    lateinit var soccerTitleAdapter:SoccerTitleAdapter

    //private val soccerTitleList:ArrayList<TituloFutbol>
    //get() = mainActivity.getSoccerTitleList()

    // vamos a crear 1 funcion que es la basica onViewCreted
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setToolBar("Titulo fragmento list",false)

        soccerTitleAdapter=SoccerTitleAdapter(MainActivity.soccerTitleList,view.context,mainActivity)


        val recyclerView= view.findViewById<RecyclerView>(R.id.rvLista)
        recyclerView.adapter=soccerTitleAdapter
    }

    // se tuvo que agregar esta funcion ya que  sirve para actualizar el corazon favorito y nofavorito al regresar
    // de el segundo activity(SoccerDetailActivity) ya que no se actualizaba elcambio del corazon
    // con esta funcion  se resuelve
    override fun onResume() {
        super.onResume()
        soccerTitleAdapter.notifyDataSetChanged()
    }


    fun onFavoriteClicked(position: Int) {
        // con esto actualiza los valores y se puede visualizar el corazon que cambia de favorito a no favorito
        soccerTitleAdapter.notifyItemChanged(position)
    }
}