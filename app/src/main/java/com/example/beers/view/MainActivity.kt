package com.example.beers.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beers.R
import com.example.beers.adapter.RecyclerViewAdapter
import com.example.beers.model.BeerModel
import com.example.beers.service.BeerApi
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.Listener {

    private val BASE_URL = "https://api.punkapi.com/"

    private var beerModels: ArrayList<BeerModel> ?= null

    private var recyclerViewAdapter: RecyclerViewAdapter ?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadData()

        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

    }


    private fun loadData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(BeerApi::class.java)

        val call = service.getData()

        call.enqueue(object: Callback<List<BeerModel>> {
            override fun onResponse(
                call: Call<List<BeerModel>>,
                response: Response<List<BeerModel>>
            ) {

                if(response.isSuccessful){
                    response.body()?.let {
                        beerModels = ArrayList(it)


                        beerModels?.let {
                            recyclerViewAdapter = RecyclerViewAdapter(it,this@MainActivity)
                            recyclerView.adapter = recyclerViewAdapter
                        }



                        /*for (beerModel: BeerModel in beerModels!!){

                            println(beerModel.name)
                            println(beerModel.tagline)
                            println(beerModel.image_url)
                            println(beerModel.description)

                        }*/

                    }

                }

            }

            override fun onFailure(call: Call<List<BeerModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    override fun onItemClick(beerModel: BeerModel) {
        Toast.makeText(this,"Clicked: ${beerModel.name}",Toast.LENGTH_LONG).show()
    }
}