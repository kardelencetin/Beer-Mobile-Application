package com.example.beers.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.beers.R
import com.example.beers.model.BeerModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_layout.view.*
import kotlin.collections.ArrayList

class RecyclerViewAdapter(private val beerList: ArrayList<BeerModel>,private val listener: Listener): RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    interface Listener{

        fun onItemClick(beerModel: BeerModel)
    }

    private val colors: Array<String> = arrayOf("#8fbc8f","#bc8f8f","#d5e8d1","#d8bfd8","#f6e3b7","#ffbbbb","#ffaa80","#bbddff","#c71585")
    class RowHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(beerModel: BeerModel,colors:Array<String>,position: Int,listener: Listener){

            itemView.setOnClickListener {
                listener.onItemClick(beerModel)
            }

            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))
            itemView.beerName.text= beerModel.name
            itemView.beerTagline.text = beerModel.tagline
            val imageView = itemView.imageView

            val url = beerModel.image_url

            Picasso.get()
                .load(url)
                .into(imageView)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return RowHolder(view)


    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {

        holder.bind(beerList[position],colors,position,listener)


    }

    override fun getItemCount(): Int {
        return  beerList.count()
    }

}