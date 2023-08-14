package fr.isen.valentin_ranc.webrattrapage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter

    private val userList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(userList) {
            val intent = Intent(this, UserDetailsActivity::class.java)
            intent.putExtra("user", it)
            startActivity(intent)
        }
        recyclerView.adapter = userAdapter


        fetchRandomUsers()

        Log.e("test", "test")
    }

    private fun fetchRandomUsers() {
        val url = "https://randomuser.me/api/?results=25"
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener<JSONObject> { response ->
                Log.e("API_SUCCESS", "SUCCESS")
                val results: JSONArray = response.getJSONArray("results")
                Log.e("API_RESPONSE", results.toString()) // Ajoutez cette ligne pour déboguer
                val gson = Gson()

                for (i in 0 until results.length()) {
                    val userObject = results.getJSONObject(i)
                    val user: User = gson.fromJson(userObject.toString(), User::class.java)
                    userList.add(user)
                }

                userAdapter.notifyDataSetChanged()
            },
            Response.ErrorListener { error ->
                Log.e("API_ERROR", error.toString())
            })

        Volley.newRequestQueue(this).add(jsonObjectRequest)
    }
}
