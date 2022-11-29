package com.example.manovmanga

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.manovmanga.Adapter.MyViewPagerAdapter
import com.example.manovmanga.Common.Common
import com.example.manovmanga.Model.Chapter
import com.example.manovmanga.databinding.ActivityMainBinding
import com.example.manovmanga.databinding.ActivityViewComicBinding
import com.wajahatkarim3.easyflipviewpager.BookFlipPageTransformer

class ViewComicActivity : AppCompatActivity() {

    lateinit var binding: ActivityViewComicBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewComicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener{
            if(Common.chapter_index == 0) {
                //IF USER IN FIRST CHAPTER BUT PRESSED BACK
                Toast.makeText(this@ViewComicActivity,"You are reading the first chapter", Toast.LENGTH_SHORT).show()

            }
            else{
                Common.chapter_index--
                fetchLinks(Common.chapterList[Common.chapter_index])
            }
        }
        binding.next.setOnClickListener{
            if(Common.chapter_index == Common.chapterList.size - 1) {
            //IF USER IN FIRST CHAPTER BUT PRESSED BACK
            Toast.makeText(this@ViewComicActivity,"You are reading the last chapter", Toast.LENGTH_SHORT).show()

        }
        else{
            Common.chapter_index++
            fetchLinks(Common.chapterList[Common.chapter_index])
        }
    }

        fetchLinks(Common.selected_chapter!!)
    }

    private fun fetchLinks(chapter: Chapter) {
        if(chapter.Links != null){
            if (chapter.Links!!.size > 0){
                val adapter = MyViewPagerAdapter(baseContext,chapter.Links!!)
                binding.viewPager.adapter = adapter
                binding.txtChapterNameViewManga.text = Common.formatString(Common.selected_chapter!!.Name!!)


                val bookFlipPageTransformer = BookFlipPageTransformer()
                bookFlipPageTransformer.scaleAmountPercent = 10f
                binding.viewPager.setPageTransformer(true,bookFlipPageTransformer)
            }
            else{
                Toast.makeText(this@ViewComicActivity,"No image here", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(this@ViewComicActivity,"This is the latest chapter from author", Toast.LENGTH_SHORT).show()
        }

    }

}