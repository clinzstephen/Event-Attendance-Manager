package com.inspiroes.attendance

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun doLogin(view: View) {
        if (usernameInput.text.toString().isEmpty() || passwordInput.text.toString().isEmpty())
            toast("Input Credentials")
        else {
            login()
            //startActivity(intentFor<EventsActivity>())

        }
    }

    fun login() {

        progress.visibility=View.VISIBLE

        doAsync {
            val body = FormBody.Builder()
                    .add("username", usernameInput.text.toString())
                    .add("password", passwordInput.text.toString())
                    .build()

            val request = Request.Builder()
                    .url("https://test3.htycoons.in/api/login")
                    .post(body)
                    .build()

            val client = OkHttpClient()

            val response = client.newCall(request).execute()

            uiThread {

                progress.visibility=View.INVISIBLE

                when (response.code()) {
                    200 -> {
                        if (response.body() != null) {
                            val jsonResponse = JSONObject(response.body()!!.string())
                            val accessToken = jsonResponse.getString("access_token")

                            Log.d("Access Token", accessToken)

                            val pref = getSharedPreferences()

                            startActivity(intentFor<EventsActivity>())
                            finish()
                        }
                    }

                    400 -> {
                        toast("Error Logging in...")
                    }
                }
            }

        }


    }
}
