package com.codebase.apitest

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONObject
import pk.codebase.requests.HttpRequest
import pk.codebase.requests.HttpResponse

class MainActivity : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getJsonObj()

        getJsonArray()

    }

    private fun getJsonObj() {
        val httpRequest = HttpRequest()

        httpRequest.setOnResponseListener { response ->
            if (response.code == HttpResponse.HTTP_OK) {

                val jsonObject: JSONObject = response.toJSONObject()
                val h1 = jsonObject.getString("Hello")

                val h2 = jsonObject.getString("Hello2")
                Log.e("h1", h1)
                Log.e("h2", h2)
                Log.e("error code", response.toJSONObject().toString() +",.,.")


                Toast.makeText(this, response.code.toString() , Toast.LENGTH_LONG).show()
                Toast.makeText(this, response.toJSONObject().toString(), Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, response.code.toString() , Toast.LENGTH_LONG).show()
            }

        }

        httpRequest.setOnErrorListener { error ->
            Log.e("error code", error.message.toString() +",.,."+ error.code)
            Toast.makeText(this, error.message , Toast.LENGTH_LONG).show()
        }

        httpRequest.get("http://192.168.0.106:8000/")
    }

    private fun getJsonArray() {
        val httpRequest = HttpRequest()

        httpRequest.setOnResponseListener { response ->
            if (response.code == HttpResponse.HTTP_OK) {
                val jsonObj = response.toJSONObject()
                val jsonArray = jsonObj.getJSONArray("json1")

                Log.e("array json", jsonArray.toString())
            }
        }

        httpRequest.setOnErrorListener { error ->
            Log.e("array json", error.toString())

        }

        httpRequest.get("http://192.168.0.106:8000/items")
    }
}