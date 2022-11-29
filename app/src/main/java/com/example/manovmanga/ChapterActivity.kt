package com.example.manovmanga

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.manovmanga.Adapter.MyChapterAdapter
import com.example.manovmanga.Common.Common
import com.example.manovmanga.Model.Manga
import com.example.manovmanga.databinding.ActivityChapterBinding
import com.example.manovmanga.databinding.ChapterItemBinding

class ChapterActivity : AppCompatActivity() {

    lateinit var binding: ActivityChapterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChapterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = Common.selected_manga!!.Name
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.recyclerChapter.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this@ChapterActivity)
        binding.recyclerChapter.layoutManager = layoutManager
        binding.recyclerChapter.addItemDecoration(DividerItemDecoration(this,layoutManager.orientation))

        fetchChapter(Common.selected_manga!!)
    }

    private fun fetchChapter(manga: Manga) {
        Common.chapterList = manga.Chapters!!
        binding.txtChapterName.text = StringBuilder("CHAPTER (")
            .append(manga.Chapters!!.size)
            .append(")")
        binding.recyclerChapter.adapter = MyChapterAdapter(this, Common.chapterList)

    }
}