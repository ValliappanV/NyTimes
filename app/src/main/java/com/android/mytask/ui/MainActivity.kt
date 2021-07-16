package com.android.mytask.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.mytask.R
import com.android.mytask.adapter.ListAdapter
import com.android.mytask.model.NewsResponse
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "NY Times Most Popular"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.menu)
        newsApi()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.more_tab_menu, menu)
        // return true so that the menu pop up is opened
        return true
    }

    private fun initializeRecyclerview(newsResponse: NewsResponse) {

        newsRecycler.setHasFixedSize(true)
        newsRecycler.removeAllViews()
        newsRecycler.layoutManager = LinearLayoutManager(this@MainActivity)
        newsRecycler.adapter = ListAdapter(
            this@MainActivity,
            this@MainActivity,
            newsResponse.results
        )
    }

    fun newsApi() {
        val progressdialog = ProgressDialog(this@MainActivity)
        progressdialog.setMessage("Please Wait....")
        progressdialog.show()
        val bodyParameters: MutableMap<String, String> = mutableMapOf()
        bodyParameters["api-key"]="V1OuC7e0uUtfjJUA4PdV6ppsINPlOrQ7"
        Log.d(
            "nytimes",
            "https://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/1.json" + " " + bodyParameters.toString()
        )
        AndroidNetworking.get("https://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/1.json").addQueryParameter(bodyParameters).setPriority(
            Priority.HIGH
        ).build().getAsJSONObject(object : JSONObjectRequestListener {
            override fun onResponse(response: JSONObject) {
                progressdialog.dismiss()
                Log.d("nytimes", response.toString())
                val NewsResponse = Gson().fromJson(response.toString(), NewsResponse::class.java)
                initializeRecyclerview(NewsResponse)
            }

            override fun onError(anError: ANError) {
                anError.printStackTrace()
                progressdialog.dismiss()
            }
        })
    }

}