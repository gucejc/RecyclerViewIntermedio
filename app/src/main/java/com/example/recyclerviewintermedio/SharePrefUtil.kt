package com.example.recyclerviewintermedio

import android.content.Context
import android.content.SharedPreferences

object SharePrefUtil {

    private lateinit var sharePreference:SharedPreferences

    fun init(context:Context){
        sharePreference=context.getSharedPreferences(
            "${BuildConfig.APPLICATION_ID}.sharedPreferences", Context.MODE_PRIVATE)
    }

    fun setSoccerTitleFavorite(id:String,value:Boolean){
        setBoolean(id,value)
    }
    private fun setBoolean(name:String,value:Boolean){
        sharePreference.edit().putBoolean(name,value).apply()
    }




    fun getSoccerTitlefavorite(id:String):Boolean{
        return getBoolean(id)
    }
    private fun  getBoolean(name:String ,defaultValue:Boolean=false):Boolean{
        return sharePreference.getBoolean(name,defaultValue)

    }
}