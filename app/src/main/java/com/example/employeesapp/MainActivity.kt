package com.example.employeesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        val queue = Volley.newRequestQueue(this)
        val url = "https://student.labranet.jamk.fi/~AA5536/MOCK_DATA.json"

        // Request a string response from the provided URL.
        val stringRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener<JSONObject> { response ->
                    val employees = response.getJSONArray("employees")
                    recyclerView.adapter = EmployeesAdapter(employees)
                },
                Response.ErrorListener { error -> Log.d("JSON", error.toString()) })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}