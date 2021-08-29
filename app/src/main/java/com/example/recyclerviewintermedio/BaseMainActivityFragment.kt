package com.example.recyclerviewintermedio

import androidx.fragment.app.Fragment

abstract class BaseMainActivityFragment(layoutId:Int):Fragment(layoutId) {

    val mainActivity:MainActivity by lazy { activity as MainActivity }

    fun setToolBar(titulo:String,habilitarRegreso:Boolean){
        mainActivity.supportActionBar?.apply {
            title=titulo
            // para que no salga la flecha de regresar en el menu superior
            setDisplayHomeAsUpEnabled(habilitarRegreso)
        }
    }
}