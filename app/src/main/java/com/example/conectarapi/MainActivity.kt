package com.example.conectarapi

/*
* Desenvolvido por Thiago da Silva Moraes
* */

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.conectarapi.adapter.MyListAdapter
import com.example.conectarapi.model.Repositorio
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    /*private lateinit var textView: TextView
    private lateinit var imageView: ImageView*/
    private var requestQueue: RequestQueue? = null

    lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Repositórios do Github"
        /*textView = findViewById(R.id.textViewResult)
        imageView = findViewById(R.id.imageViewResult)*/
        listView = findViewById(R.id.listViewResult)

        requestQueue = Volley.newRequestQueue(this)

        jsonParse()

    }
    private fun jsonParse() {
        val url = "https://api.github.com/search/repositories?q=language:swift&sort=stars"
        val request = JsonObjectRequest(Request.Method.GET, url, null, {
                response ->try {
            val jsonArray = response.getJSONArray("items")
            var list = mutableListOf<Repositorio>()

            for (i in 0 until jsonArray.length()) {
                val employee = jsonArray.getJSONObject(i)
                val nome = employee.getString("name")
                val novoArray = employee.getJSONObject("owner")
                val avatar = novoArray.getString("avatar_url")

                val url = "${avatar}"


                list.add(Repositorio(avatar, nome))
                /*Picasso.get().load(url)
                   .into(imageView)
                //imageView.setImageURI(Uri.parse(avatar.getString("avatar_url")))
                //textView.append("$nome, ${avatar.getString("avatar_url")}\n\n")*/

                listView.setOnItemClickListener{parent, view, position, id ->
                    Toast.makeText(this@MainActivity, "Teste de clique!",   Toast.LENGTH_SHORT).show()
                }
            }
            listView.adapter = MyListAdapter(this,R.layout.item, list)

        } catch (e: JSONException) {
            e.printStackTrace()
        } finally {
            Log.e("Exception", "Chegou no finally")
        }
        }, { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }
}