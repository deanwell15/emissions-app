package com.deanwell.emissions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.deanwell.emissions.model.More
import com.deanwell.testlist.model.Coord
import com.deanwell.testlist.model.ReqresItem
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.my_recycler_view
import kotlinx.android.synthetic.main.activity_more.*

class MoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more)

        val resp = intent.getStringExtra("more")
        val more = Gson().fromJson(resp, More::class.java)

        val info: String = "Airplane: ${more.airplane}\nCoefficient of the seats (less): ${more.coefficientoftheseatsless}\n" +
                "Coefficient of the seats (max): ${more.coefficientoftheseatsmax}\nDate: ${more.data}\n" +
                "Emissions for point (less): ${more.emissionsforpointless}\nEmissions for point (max): ${more.emissionsforpointmax}\n" +
                "Km: ${more.km}\nTotal emissions (less): ${more.totalemissionsless}\nTotal emissions (max): ${more.totalemissionsmax}\n" +
                "Total fuel wasted (less): ${more.totalfuelwastedless}\nTotal fuel wasted (max): ${more.totalfuelwastedmax}"

        more_text.text = info
    }
}