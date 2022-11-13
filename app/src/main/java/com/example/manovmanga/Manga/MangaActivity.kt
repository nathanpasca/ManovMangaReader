package com.example.manovmanga.Manga

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.manovmanga.Adapter.MyMangaAdapter
import com.example.manovmanga.Adapter.MySliderAdapter
import com.example.manovmanga.Common.Common.mangaList
import com.example.manovmanga.Interface.IBannerLoadDoneListener
import com.example.manovmanga.Interface.IMangaLoadDoneListener
import com.example.manovmanga.Model.Manga
import com.example.manovmanga.R
import com.example.manovmanga.Service.PicassoLoadingService
import com.example.manovmanga.databinding.ActivityMangaBinding
import com.example.manovmanga.databinding.ActivitySignInBinding
import com.google.android.gms.common.internal.service.Common
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import dmax.dialog.SpotsDialog
import ss.com.bannerslider.Slider
import ss.com.bannerslider.adapters.SliderAdapter

class MangaActivity : AppCompatActivity(), IBannerLoadDoneListener, IMangaLoadDoneListener {


    //Database
    internal lateinit var banners_ref: DatabaseReference
    lateinit var manga_ref: DatabaseReference
    lateinit var binding: ActivityMangaBinding

    //Listener
    lateinit var iBannerLoadDoneListener: IBannerLoadDoneListener
    lateinit var iMangaLoadDoneListener: IMangaLoadDoneListener

    lateinit var alertDialog: AlertDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMangaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initialize Listener
        iBannerLoadDoneListener = this
        iMangaLoadDoneListener = this

        //Initialize Dialog
        alertDialog = SpotsDialog.Builder().setContext(this@MangaActivity)
            .setCancelable(false)
            .setMessage("Please Wait...")
            .build()

        //Inittialize Database
        banners_ref = FirebaseDatabase.getInstance().getReference("Banners")
        manga_ref = FirebaseDatabase.getInstance().getReference("Manga")

        //Load Banner and Manga
        binding.swipeToRefresh.setColorSchemeColors(
            com.google.android.material.R.attr.colorPrimary,
            com.google.android.material.R.attr.colorPrimaryDark
        )

        binding.swipeToRefresh.setOnRefreshListener {
            loadBanners()
            loadManga()
        }
        binding.swipeToRefresh.post {
            loadBanners()
            loadManga()
        }

        Slider.init(PicassoLoadingService())

        binding.recyclerManga.setHasFixedSize(true)
        binding.recyclerManga.layoutManager = LinearLayoutManager(this@MangaActivity)

    }

    private fun loadManga() {

        alertDialog.show()

        manga_ref.addListenerForSingleValueEvent(object : ValueEventListener {
            var manga_load: MutableList<Manga> = ArrayList<Manga>()
            override fun onDataChange(snapshot: DataSnapshot) {
                for (mangaSnapShot in snapshot.children) {
                    val manga = mangaSnapShot.getValue(Manga::class.java)
                    manga_load.add(manga!!)
                }

                iMangaLoadDoneListener.onMangaLoadDoneListener(manga_load)


            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MangaActivity, "" + error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun loadBanners() {
        banners_ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val banner_list = ArrayList<String>()
                for (banner in snapshot.children) {
                    val image = banner.getValue(String::class.java)
                    banner_list.add(image!!)
                }
                iBannerLoadDoneListener.onBannerLoadDoneListener(banner_list)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MangaActivity, "" + error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onBannerLoadDoneListener(banners: List<String>) {
        binding.slider.setAdapter(MySliderAdapter(banners))

    }

    override fun onMangaLoadDoneListener(mangaList: List<Manga>) {
        alertDialog.dismiss()
        com.example.manovmanga.Common.Common.mangaList = mangaList
        binding.recyclerManga.adapter = MyMangaAdapter(baseContext, mangaList)
        binding.txtManga.text = StringBuilder("NEW MANGA! (")
            .append(mangaList.size)
            .append(")")
        if (binding.swipeToRefresh.isRefreshing)
            binding.swipeToRefresh.isRefreshing = false
    }
}