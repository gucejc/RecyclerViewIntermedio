package com.example.recyclerviewintermedio

import android.app.Application

class EplApplication: Application() {

    companion object{
        lateinit var  application: EplApplication
        lateinit var  soccerTitleList:ArrayList<TituloFutbol>

    }

    override fun onCreate() {
        super.onCreate()
        application=this

        SharePrefUtil.init(this)
    }
}