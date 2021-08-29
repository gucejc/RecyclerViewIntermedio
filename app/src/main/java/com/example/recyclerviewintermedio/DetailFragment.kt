package com.example.recyclerviewintermedio

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.squareup.picasso.Picasso

class DetailFragment:BaseMainActivityFragment(R.layout.fragment_detail) {

    private val soccerTitleData:TituloFutbol by lazy {
        MainActivity.soccerTitleList.find {
            //arguments?.getString("tituloFutbolId") es traer el argumento del primer fragmento  "tituloFutbolId"
            it.id==arguments?.getString("tituloFutbolId")
        }?:TituloFutbol()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

        //creamos esta funcion que esta en la clase abstracta BAseMainActivityFragment para poder hacer el toolbar
        //con los  datos  de el titulo y la habilitación de la flecha de regreso
        setToolBar("Titulo de fragmento Detail",true)


        setHasOptionsMenu(true)


        val imageDetail: ImageView =view.findViewById(R.id.ivImagenDetail)
        val tituloDeatil: TextView =view.findViewById(R.id.tvTitleDetail)
        val subtituloDetail: TextView =view.findViewById(R.id.tvSubtitleDetail)
        val infoDetail: TextView =view.findViewById(R.id.tvDetailLong)

        //imagen obtenida directamente de la app
        //imageDetail.setImageResource(soccerTitleData.headerImageResId)

        //piccasso es una libreria que la colocamos en el gradle
        // es necesario agregar permisos de internet
        //ingresamos picasso para poder desplegar la imagen directamente de la url ya que anteriormente lo teniamos en la app
        Picasso.get().isLoggingEnabled=true
        Picasso.get().
            load(soccerTitleData.headerImegeUrl).// url de la imagen
            placeholder(R.mipmap.ic_launcher). // sirve para agregar una imagen mientras carga la que trae de la url
            into(imageDetail); // la imagen la coloca en

        tituloDeatil.text=soccerTitleData.title
        subtituloDetail.text=soccerTitleData.description
        infoDetail.text=soccerTitleData.descriptionLong
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_soccer_detail,menu)

        if(soccerTitleData.isFavorite){
            menu?.findItem(R.id.menuFavorite)?.setIcon(R.drawable.ic_favorite)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            //para que salga del fragmento o lo borre y se vaya al primer fragmento
            android.R.id.home ->{
                //regresa al fragmento anterior
                (activity as MainActivity)?.supportFragmentManager.popBackStack()

                true
            }
            R.id.menuLink->{
                Log.i("URL",soccerTitleData.teamUrl)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(soccerTitleData.teamUrl))
                startActivity(intent)
                true
            }
            R.id.menuFavorite->{
                val  isFavorited=soccerTitleData.isFavorite
                Log.i("favorite","$isFavorited")
                if(isFavorited){
                    item.setIcon(R.drawable.ic_favorite_border)
                }else{
                    item.setIcon(R.drawable.ic_favorite)
                }
                soccerTitleData.isFavorite=!isFavorited

                SharePrefUtil.setSoccerTitleFavorite(soccerTitleData.id,soccerTitleData.isFavorite)
                true
            }
            else ->super.onOptionsItemSelected(item)
        }

    }

}