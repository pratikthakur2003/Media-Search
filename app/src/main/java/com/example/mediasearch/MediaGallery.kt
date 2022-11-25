package com.example.mediasearch

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_media_gallery.*
import kotlinx.android.synthetic.main.item_media.*


class MediaGallery : AppCompatActivity(), ImageItemClicked {
    companion object{
        var Query : String? = null
    }

    private lateinit var mAdapter : mediaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_gallery)
        Query =intent.getStringExtra("SearchQuery")
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchData()
        mAdapter = mediaAdapter(this)
        recyclerView.adapter = mAdapter

    }



    private fun fetchData(){
//        progressBar.visibility = View.VISIBLE
//        val list : ArrayList<ItemsViewModel> = ArrayList<ItemsViewModel>()
//        list.add(ItemsViewModel(R.drawable.download))
//        list.add(ItemsViewModel(R.drawable.download_1))
//        list.add(ItemsViewModel(R.drawable.download_2))
//        list.add(ItemsViewModel(R.drawable.download_3))
//        list.add(ItemsViewModel(R.drawable.download_4))
//        list.add(ItemsViewModel(R.drawable.download_5))
//        list.add(ItemsViewModel(R.drawable.download_6))
//        list.add(ItemsViewModel(R.drawable.download_7))
//        val queue = Volley.newRequestQueue(this)
        val url = "https://pixabay.com/api/?key=25662268-2b0f75a3f66d80a25d1fde2fd&q=$Query&image_type=photo&pretty=true"

        // Request a string response from the provided URL.

        val req = JsonObjectRequest(
            Request.Method.GET ,
            url ,
            null,
            {
                val imageJsonArray = it.getJSONArray("hits")
                val imageArray = ArrayList<ItemsViewModel>()

                for(i in 0 until imageJsonArray.length()){
                    val imageObject = imageJsonArray.getJSONObject(i)
                    val imageItem = ItemsViewModel(imageObject.getString("webformatURL") , (imageObject.getString("pageURL")))

                    imageArray.add(imageItem)
                }
                mAdapter.updateImage(imageArray)
            },
            {

            }
        )

        MySingleton.getInstance(this).addToRequestQueue(req)


    }

    override fun onItemClicked(item: ItemsViewModel) {

        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.imageUrl))
    }
}