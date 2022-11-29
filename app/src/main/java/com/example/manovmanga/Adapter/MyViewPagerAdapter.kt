package com.example.manovmanga.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.manovmanga.R
import com.github.chrisbanes.photoview.PhotoView
import com.squareup.picasso.Picasso

class MyViewPagerAdapter(internal var context: Context, internal var linkList:List<String>):PagerAdapter() {
    internal var inflater:LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
    }
    override fun getCount(): Int {
        return linkList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val image_layout = inflater.inflate(R.layout.view_pager_item,container,false)

        val page_image = image_layout.findViewById(R.id.page_image) as PhotoView
        Picasso.get().load(linkList[position]).into(page_image)

        container.addView(image_layout)
        return image_layout
    }

}