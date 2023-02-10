package com.phone.movieverse

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class movieadapter(val context:Context,val movielist:List<SearchX>): RecyclerView.Adapter<movieviewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): movieviewholder {
        val view=LayoutInflater.from(context).inflate(R.layout.movie_elements,parent,false)
        return movieviewholder(view)
    }

    override fun getItemCount(): Int {
        return movielist.size
    }

    override fun onBindViewHolder(holder: movieviewholder, position: Int) {
        val moviedata=movielist[position]
        Glide.with(context).load(moviedata.image).into(holder.imageview)
        holder.textview.text=moviedata.title

        holder.imageview.setOnClickListener {
            val movieid=movielist[position].movieid
            val intent:Intent=Intent(context,MovieDetails::class.java)
            intent.putExtra("movieid",movieid)
            context.startActivity(intent)
        }
    }

}

class movieviewholder(itemview: View):RecyclerView.ViewHolder(itemview){
    val imageview=itemview.findViewById<ImageView>(R.id.imagecardview)
    val textview=itemview.findViewById<TextView>(R.id.t1)
}