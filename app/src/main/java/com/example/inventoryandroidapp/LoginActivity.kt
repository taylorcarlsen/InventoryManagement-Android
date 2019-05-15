package com.example.inventoryandroidapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.example.inventoryandroidapp.models.User
import com.example.inventoryandroidapp.services.ServiceBuilder
import com.example.inventoryandroidapp.services.UserService

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.CacheResponse

class LoginActivity : AppCompatActivity() {

    lateinit var stringResponse: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)
        var mContext: Context = this

        fab.setOnClickListener { view ->

            stringResponse = ""
            var employeeId = etEmployeeID.text.toString()
            var intEmployeeId = employeeId.toInt()
            var employeePassword = etPassword.text.toString()
            var userService: UserService = ServiceBuilder.builderService(UserService::class.java)
            var userResquest: Call<User> = userService.getUser(intEmployeeId)

            userResquest.enqueue(object: Callback<User>{
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Snackbar.make(view, "Failed to get user.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    var responsePassword = response.body()?.password
                    if (employeePassword == responsePassword){

                        val activityIntent = Intent(mContext, MainActivity::class.java)
                        activityIntent.putExtra("EMPLOYEE_ID", etEmployeeID.text.toString())
                        startActivity(activityIntent)

                    }else{
                        Snackbar.make(view, "Login Incorrect.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    }
                }

            })
        }
    }

}
