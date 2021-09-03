package com.example.recyclerviewintermedio

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import java.util.zip.Inflater

class SoccerDetailActivity : AppCompatActivity() {

    private lateinit var soccerTitleData:TituloFutbol
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soccer_detail)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title="Nuevo titulo con cambio a example branch"
        }



///////////////////////////////////////////// Aqui un camino para  hacer la petición de intent serializable////////////////////////
        // super ojo  se debe asosiar el dato serializable  a la data clase TituloFutbol de lo contrario no jala
        // o en su defecto lo podemos dejar como la parte comentada
        //se asocia a la data clase  y a su vez se valida que acepte valores nulos (?) y si existen esos  valores
        // se forza a  colocar el texto  de (title = "falla en el sistema ....etc) para que que no truene  la app
        /***********************************Esto no va comentado*************************************/
        //soccerTitleData=intent.getSerializableExtra("tituloFutbol") as TituloFutbol
        /***********************************Esto no va comentado*************************************/
          /*      as? TituloFutbol ?: TituloFutbol(
                                                    title = "falla en el sistema ",
                                                    descriptionLong = " Ups existe un proble intenta mas tarde")
        */


        //

        val selectedSoccerId=intent.getStringExtra("tituloFutbolId")
        Log.i("tituloFutbolId",selectedSoccerId.toString())
        soccerTitleData=MainActivity.soccerTitleList.find {
            it.id==selectedSoccerId
        }?: TituloFutbol(
            title = "Falla en el Sistema ",
            descriptionLong = " Ups existe un proble intenta mas tarde")
        val imageDetail:ImageView=findViewById(R.id.ivImagenDetail)
        val tituloDeatil:TextView=findViewById(R.id.tvTitleDetail)
        val subtituloDetail:TextView=findViewById(R.id.tvSubtitleDetail)
        val infoDetail:TextView=findViewById(R.id.tvDetailLong)

        imageDetail.setImageResource(soccerTitleData.headerImageResId)

        tituloDeatil.text=soccerTitleData.title
        subtituloDetail.text=soccerTitleData.description
        infoDetail.text=soccerTitleData.descriptionLong


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                finish()
                true
            }
            R.id.menuLink->{
               Log.i("URL",soccerTitleData.teamUrl)
                val intent =Intent(Intent.ACTION_VIEW, Uri.parse(soccerTitleData.teamUrl))
                startActivity(intent)
                true
            }
            R.id.menuFavorite->{
                val  isFavorited=soccerTitleData.isFavorite
                Log.i("favorite","$isFavorited")
                if(isFavorited){
                    item.setIcon(R.drawable.ic_favorite_border)
                }
                else{
                    item.setIcon(R.drawable.ic_favorite)
                }

                soccerTitleData.isFavorite=!isFavorited
                true
            }
            else ->super.onOptionsItemSelected(item)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_soccer_detail,menu)

        if(soccerTitleData.isFavorite){
            menu?.findItem(R.id.menuFavorite)?.setIcon(R.drawable.ic_favorite)
        }
        return true
    }



}