package com.example.manovmanga.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.manovmanga.Common.Common
import com.example.manovmanga.Interface.IRecyclerClick
import com.example.manovmanga.Model.Chapter
import com.example.manovmanga.R
import com.example.manovmanga.ViewComicActivity

class MyChapterAdapter(internal var context: Context,
                       internal var chapterList:List<Chapter>):RecyclerView.Adapter<MyChapterAdapter.MyViewHolder>() {



    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView), View.OnClickListener {

        internal var txt_chapter_number: TextView
        internal lateinit var iRecyclerClick: IRecyclerClick


        fun setClick(iRecyclerClick: IRecyclerClick){
            this.iRecyclerClick = iRecyclerClick
        }

        init {
            txt_chapter_number = itemView.findViewById(R.id.txt_chapter_number) as TextView

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            iRecyclerClick.onClick(v!!,adapterPosition)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.chapter_item, parent , false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txt_chapter_number.text = StringBuilder(chapterList[position].Name)
        holder.setClick(object :IRecyclerClick{
            override fun onClick(view: View, position: Int) {
                Common.selected_chapter = chapterList[position]
                Common.chapter_index = position
                context.startActivity(Intent(context,ViewComicActivity::class.java))
            }

        })
    }

    override fun getItemCount(): Int {
        return chapterList.size
    }
}