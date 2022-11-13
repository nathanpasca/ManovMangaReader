package com.example.manovmanga.Interface

import com.example.manovmanga.Model.Manga

interface IMangaLoadDoneListener {
    fun onMangaLoadDoneListener(mangaList:List<Manga>)
}