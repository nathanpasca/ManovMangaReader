package com.example.manovmanga.Common

import com.example.manovmanga.Model.Chapter
import com.example.manovmanga.Model.Manga

object Common {
    fun formatString(name: String): String {
        val finalResult = StringBuilder(if(name.length > 15)name.substring(0,15)+"..." else name)
        return finalResult.toString()
    }

    var chapter_index: Int=-1
    lateinit var selected_chapter: Chapter
    lateinit var chapterList: List<Chapter>
    var selected_manga: Manga? = null
    var mangaList: List<Manga> = ArrayList<Manga>()
}