

package com.example.recyclerviewintermedio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.ActionMode
import android.widget.Button
import android.widget.Toast
import androidx.core.view.GravityCompat.apply
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView





class MainActivity : AppCompatActivity(),SoccerTitleInterface {

    //hace que la lista soccerTitleList se haga static , esto cn la idea de guardar algun dato fijo
   companion object{
        lateinit var soccerTitleList: ArrayList<TituloFutbol>
   }

    //private lateinit var soccerTitleAdapter:SoccerTitleAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        soccerTitleList =getSoccerTitleList()
        val context= this





       // supportFragmentManager.beginTransaction().setReorderingAllowed(true).
       // add(R.id.fragmentContainerView,ListFragment()).commit()

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragmentContainerView,ListFragment())
        }


    }




    // funcion que  sirve para el pulsar el boton  y cambiar a otro activity
    override fun onDetailButtonClicked(position: Int) {
       /* val soccerTitile=soccerTitleList[position]
        val intent=Intent(this,SoccerDetailActivity::class.java).apply {
            //ojo este valor lo pusimos como serializable  para que se pudiera enviar
            //como un array,tambien sobre la dataClase TituloFutbol se colocó
            //el regreso de la funcion como serializable
            putExtra("tituloFutbolId",soccerTitile.id)
        }
        startActivity(intent)
        */
        val soccerTitile=soccerTitleList[position]
        // agregamos el bundle con argumentos y esos parametros se agregan
        //al segundo fragmento
        val bundle= Bundle()
        bundle.putString("tituloFutbolId",soccerTitile.id)

        // aqui una forma  de pasare parametros  y cambiar fragmentos

        supportFragmentManager.beginTransaction().
        setCustomAnimations(
            R.anim.custom_fragment_slide_in_right,// yo lo cree y esta en lacarpeta anim
            R.anim.custom_fragment_slide_out_left,
            android.R.anim.slide_in_left,
            android.R.anim.slide_out_right).
        setReorderingAllowed(true).
        addToBackStack(null).// se implementa para que al pulsar regresar pueda pasar  al primer fragmento
        replace(R.id.fragmentContainerView,DetailFragment().apply { arguments=bundle }).commit()

        // aqui otra forma de pasar datos y cambiar fragmentos

        /*supportFragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack(null)

            replace(R.id.fragmentContainerView,DetailFragment().apply { arguments=bundle })
        };
        */

    }


    // funcion onterfase que cambia el corazon  de favorito yno favorito
    override fun onFavoriteClicked(position: Int) {


        Toast.makeText(applicationContext,"Pulsaste el boton favorito",Toast.LENGTH_LONG).show()
        //con estas 2 lineas activas o desactivas el corazon de favorito
        //dentro del cardview de la primera pantalla (fragmento1)
        val soccerTitle = soccerTitleList[position]
        soccerTitle.isFavorite= !soccerTitle.isFavorite

        // con esto llamamos al la funcion onFavoriteClicked pero del fragmento y en el
        //fragmento actualiza con soccerTitleAdapter.notifyItemChanged(position)
        (supportFragmentManager.fragments[0] as? ListFragment)?.onFavoriteClicked(position)

        SharePrefUtil.setSoccerTitleFavorite(soccerTitle.id,soccerTitle.isFavorite)


    }

    fun getSoccerTitleList():ArrayList<TituloFutbol>{

        return  ArrayList<TituloFutbol>().apply {

            add(TituloFutbol(
                    id = "manchester united",
                    title = "Manchester United",
                    description = "Esta es una description corta",
                    descriptionLong = "Esta descripción es un poco las larga para que se vea la diferencia entre la cantidad de texto de uno u de otro ",
                    buttonText = "Detalles",
                    headerImageResId = R.drawable.manu_header,
                    headerImegeUrl = "https://64.media.tumblr.com/21c9fb4474214e15eed8a623fa0fdfa0/tumblr_od5ggv1q7T1ude0uno1_500h.jpg",
                    teamUrl = "https://www.manutd.com/",
                    isFavorite = SharePrefUtil.getSoccerTitlefavorite("manchester united")
            ))
            add(TituloFutbol(
                    id = "manchester city",
                    title = "Manchester City",
                    description = "Esta es una description corta",
                    descriptionLong = "Esta descripción es un poco las larga para que se vea la diferencia entre la cantidad de texto de uno u de otro ",
                    buttonText = "Detalles",
                    headerImageResId = R.drawable.man_city,
                    headerImegeUrl = "https://pbs.twimg.com/media/CrNZBNPW8AAub_1.jpg",
                    teamUrl = "https://es.mancity.com/" ,
                    isFavorite = SharePrefUtil.getSoccerTitlefavorite("manchester city")
            ))
            add(TituloFutbol(
                    id = "tottenham",
                    title = "Tottenham",
                    description = "Esta es una description corta",
                    descriptionLong = "Esta descripción es un poco las larga para que se vea la diferencia entre la cantidad de texto de uno u de otro ",
                    buttonText = "Detalles",
                    headerImageResId = R.drawable.tottenham_header,
                    headerImegeUrl = "https://pbs.twimg.com/media/CsEWC3UXgAA3o-u.jpg" ,
                    teamUrl = "https://www.tottenhamhotspur.com/",
                    isFavorite = SharePrefUtil.getSoccerTitlefavorite("tottenham")

            ))
            add(TituloFutbol(
                    id = "arsenal",
                    title = "Arsenal",
                    description = "Esta es una description corta",
                    descriptionLong = "Esta descripción es un poco las larga para que se vea la diferencia entre la cantidad de texto de uno u de otro ",
                    buttonText = "Detalles",
                    headerImageResId = R.drawable.arsenal_header,
                    headerImegeUrl = "https://i.pinimg.com/originals/00/b9/57/00b957e908fd86d72b3e014892d4b895.jpg",
                    teamUrl = "https://www.arsenal.com/",
                    isFavorite = SharePrefUtil.getSoccerTitlefavorite("arsenal")
            ))
            add(TituloFutbol(
                    id = "chealsea",
                    title = "Chealse",
                    description = "Esta es una description corta",
                    descriptionLong = "Esta descripción es un poco las larga para que se vea la diferencia entre la cantidad de texto de uno u de otro ",
                    buttonText = "Detalles",
                    headerImageResId = R.drawable.chelsea_header,
                    headerImegeUrl = "https://pbs.twimg.com/media/CrNZBNPW8AAub_1.jpg",
                    teamUrl = "https://www.chelseafc.com/es",
                    isFavorite = SharePrefUtil.getSoccerTitlefavorite("chealsea")

            ))
        }

        //man u    https://64.media.tumblr.com/21c9fb4474214e15eed8a623fa0fdfa0/tumblr_od5ggv1q7T1ude0uno1_500h.jpg
        //man city https://pbs.twimg.com/media/CrNZBNPW8AAub_1.jpg
        //arsenal  https://i.pinimg.com/originals/00/b9/57/00b957e908fd86d72b3e014892d4b895.jpg
        //tottenham https://pbs.twimg.com/media/CsEWC3UXgAA3o-u.jpg
        //chelsea   https://pbs.twimg.com/media/CrNZBNPW8AAub_1.jpg
        //
    }
}