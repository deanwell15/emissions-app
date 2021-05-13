package com.deanwell.emissions

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.deanwell.testlist.model.Coord
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        val resp = intent.getStringExtra("coords")
        val coords = Gson().fromJson(resp, Coord::class.java)
        var last: LatLng = LatLng(0.0, 0.0)
        coords.forEach { item ->
            val dot = LatLng(item.latitude.toDouble(), item.longitude.toDouble())
            map.addMarker(MarkerOptions().position(dot).title("${item.latitude}, ${item.longitude}"))
            last = dot
        }

        map.moveCamera(CameraUpdateFactory.newLatLng(last))
    }

}