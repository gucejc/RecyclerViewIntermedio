package com.example.recyclerviewintermedio

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

// ojo private val data:ArrayList<TituloFutbol> lo pasamos  como un parametro de entrada como si fuera un constructor
class SoccerTitleAdapter(private val data:ArrayList<TituloFutbol>,
                         private val context:Context,
                         private val soccerTitleInterface:SoccerTitleInterface
                        )
                         :RecyclerView.Adapter<SoccerTitleAdapter.SoccerTitleViewHolder>(){


    inner class SoccerTitleViewHolder(parent: ViewGroup):RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.view_holder_soccer_title,parent,false)

    )
    {
        //ingresamos los objetos de la vista en este caso el cardview, los text edit el boton etc

        private val ivImage:ImageView=itemView.findViewById(R.id.ivImagen)
        private val tvTitle:TextView=itemView.findViewById(R.id.tvTitle)
        private val tvSubtitle:TextView=itemView.findViewById(R.id.tvSubtitle)
        private val btnDescribe:Button=itemView.findViewById(R.id.btnDescription)
        private val ivFavoriteImage:AppCompatImageView=itemView.findViewById(R.id.IvFavoriteImage)



         fun onBind(soccerTitle:TituloFutbol,soccerTitleInterface: SoccerTitleInterface){


             ivImage.setImageResource(soccerTitle.headerImageResId)
             tvTitle.text=soccerTitle.title
             tvSubtitle.text=soccerTitle.description
             ivFavoriteImage.setImageResource(soccerTitle.headerImageFavorite)

             btnDescribe.setOnClickListener {


                 Log.i("Button","valor de button ")

                //Toast.makeText(it.context, "${adapterPosition }hola", Toast.LENGTH_SHORT).show()
                 soccerTitleInterface.onDetailButtonClicked(adapterPosition)
             }
             val icoFavorite:Int
             if(soccerTitle.isFavorite){
                 icoFavorite=R.drawable.ic_favorite
             }else{
                 icoFavorite=R.drawable.ic_favorite_border
             }
             ivFavoriteImage.setImageResource(icoFavorite)
             ivFavoriteImage.setOnClickListener {
                 soccerTitleInterface.onFavoriteClicked(adapterPosition)
             }

         }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoccerTitleViewHolder {
       return  SoccerTitleViewHolder(parent)
    }

    override fun onBindViewHolder(holder: SoccerTitleViewHolder, position: Int) {
        holder.onBind(data[position],soccerTitleInterface)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}