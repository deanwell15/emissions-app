package com.deanwell.emissions

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.deanwell.testlist.model.Reqres
import com.deanwell.testlist.model.ReqresItem
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import androidx.lifecycle.lifecycleScope
import com.deanwell.testlist.model.Coord
import com.deanwell.testlist.model.CoordItem
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {


    var dataList: MutableList<ReqresItem> = mutableListOf()
    var displayList: MutableList<ReqresItem> = mutableListOf()
    var main_context = this

    //private lateinit var myAdapter: MyAdapter
    var json: JSONObject = JSONObject()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        my_recycler_view.layoutManager = LinearLayoutManager(this)
        my_recycler_view.addItemDecoration(DividerItemDecoration(this, OrientationHelper.VERTICAL))
        my_recycler_view.adapter = MyAdapter(dataList)

        val url = "http://195.133.1.227:5555/get_plane"

        doAsync {
            val resp = URL(url).readText()
            val res = Gson().fromJson(resp, Reqres::class.java)

            uiThread {
                dataList.addAll(res)
                displayList.addAll(res)
                val adapt = MyAdapter(displayList)
                my_recycler_view.adapter = adapt

                adapt.onButtonClick = { data ->

                    doAsync {

                        //Toast.makeText(applicationContext, data.flight, Toast.LENGTH_SHORT).show()
                        val more_url = "http://195.133.1.227:5555/get_more?fn=${
                            data.flight.replace(
                                " ",
                                "+"
                            )
                        }&plane=${data.airplane.replace(" ", "+")}&date=${
                            data.date.replace(
                                " ",
                                "+"
                            )
                        }"
                        Log.i(MainActivity::class.java.toString(), more_url)
                        val resp = URL(more_url).readText()
                        val MoreIntent = Intent(main_context, MoreActivity::class.java)
                        MoreIntent.putExtra("more", resp)
                        startActivity(MoreIntent)

                    }
                }

                adapt.onItemClick = { data ->

                    doAsync {
                        val get_url = "http://195.133.1.227:5555/get_coordinates?fn=${data.flight.replace(" ", "+")}&plane=${data.airplane.replace(" ", "+")}&date=${data.date.replace(" ", "+")}"
                        Log.i(MainActivity::class.java.toString(), get_url)
                        val resp = URL(get_url).readText()
                        //val res = Gson().fromJson(resp, Coord::class.java)
                        //res.forEach { coordItem -> rr.add(coordItem)}

                        //Log.i(MainActivity::class.java.toString(), res[0].latitude + res[0].longitude)
                        //Log.i(MainActivity::class.java.toString(), coordItem.latitude + " " + coordItem.longitude)

                        val MapIntent = Intent(main_context, MapsActivity::class.java)
                        MapIntent.putExtra("coords", resp)
                        startActivity(MapIntent)
                    }
                    //Toast.makeText(applicationContext, data.flight, Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.action_search, menu)
        val menuItem = menu!!.findItem(R.id.action_search)

        if (menuItem != null) {

            val searchView = menuItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    if (newText!!.isNotEmpty()) {
                        displayList.clear()
                        val search = newText.toLowerCase(Locale.getDefault())
                        dataList.forEach {
                            if (it.flight.toLowerCase(Locale.getDefault()).contains(search) ||
                                it.airplane.toLowerCase(Locale.getDefault()).contains(search) ||
                                it.date.toLowerCase(Locale.getDefault()).contains(search) ||
                                it.from.toLowerCase(Locale.getDefault()).contains(search) ||
                                it.to.toLowerCase(Locale.getDefault()).contains(search) ) {
                                    displayList.add(it)
                            }
                        }

                        my_recycler_view.adapter!!.notifyDataSetChanged()

                    } else {
                        displayList.clear()
                        displayList.addAll(dataList)
                        my_recycler_view.adapter!!.notifyDataSetChanged()
                    }

                    return true
                }

            })
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

}