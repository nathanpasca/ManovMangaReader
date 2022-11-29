package com.example.manovmanga.Adapter

import android.content.Context
import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.manovmanga.ChapterActivity
import com.example.manovmanga.Common.Common
import com.example.manovmanga.Interface.IRecyclerClick
import com.example.manovmanga.Model.Manga
import com.example.manovmanga.R
import com.squareup.picasso.Picasso

class MyMangaAdapter(internal var context : Context, internal var mangaList : List<Manga>):RecyclerView.Adapter<MyMangaAdapter.MyViewHolder>()
{
    class MyViewHolder(itemView: View): ViewHolder(itemView), View.OnClickListener {
            val mangaImage : ImageView = itemView.findViewById(R.id.mangaImageView)
            val mangaTitle : TextView = itemView.findViewById(R.id.mangaTitleTextView)
            val mangaAuthor : TextView = itemView.findViewById(R.id.mangaAuthorTextView)
            val mangaStatus : TextView = itemView.findViewById(R.id.mangaStatusTextView)
            val mangaCategory : TextView = itemView.findViewById(R.id.mangaCategoryTextView)
            val mangaDescription : TextView = itemView.findViewById(R.id.mangaDescriptionTextView)

        lateinit var iRecyclerClick: IRecyclerClick

        fun setClick(iRecyclerClick: IRecyclerClick) {
            this.iRecyclerClick = iRecyclerClick
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            iRecyclerClick.onClick(v!!,adapterPosition)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.manga_item, parent , false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(mangaList[position].Image).into(holder.mangaImage)
        holder.mangaTitle.setText(mangaList.get(position).Name)
        holder.mangaAuthor.setText(mangaList.get(position).Author)
        holder.mangaStatus.setText(mangaList.get(position).Status)
        holder.mangaCategory.setText(mangaList.get(position).Category)
        holder.mangaDescription.setText(mangaList.get(position).Description)

        holder.setClick(object:IRecyclerClick{
            override fun onClick(view: View, position: Int) {
                val intent = Intent(context,ChapterActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(context,intent,null)
                Common.selected_manga=mangaList[position]
            }

        })

    }

    override fun getItemCount(): Int {
        return mangaList.size
    }



}