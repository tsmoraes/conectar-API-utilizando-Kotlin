package com.example.conectarapi.adapter

/*
* Desenvolvido por Thiago da Silva Moraes
* */

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.conectarapi.R
import com.example.conectarapi.model.Repositorio
import com.squareup.picasso.Picasso

class MyListAdapter(var mCtx:Context , var resource:Int,var items:List<Repositorio>)
    :ArrayAdapter<Repositorio>( mCtx , resource , items ){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater :LayoutInflater = LayoutInflater.from(mCtx)

        val view : View = layoutInflater.inflate(resource , null )
        val imageView :ImageView = view.findViewById(R.id.imageViewResult)
        var textView : TextView = view.findViewById(R.id.textViewResult)


        var person : Repositorio = items[position]

        Picasso.get().load(person.avatar)
            .into(imageView)
        //imageView.setImageDrawable(mCtx.resources.getDrawable(person.photo))
        textView.text = person.nome


        return view
    }

}