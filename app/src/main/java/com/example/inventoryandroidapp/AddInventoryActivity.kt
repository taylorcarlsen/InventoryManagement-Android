package com.example.inventoryandroidapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.example.inventoryandroidapp.models.Inventory
import com.example.inventoryandroidapp.services.InventoryService
import com.example.inventoryandroidapp.services.ServiceBuilder

import kotlinx.android.synthetic.main.activity_add_inventory.*
import kotlinx.android.synthetic.main.content_add_inventory.*
import kotlinx.android.synthetic.main.content_add_item.tvInventoryItemCode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class AddInventoryActivity : AppCompatActivity() {

    private var itemNumber = ""
    private var baseQuantity = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_inventory)
        setSupportActionBar(toolbar)
        val mContext: Context = this

        fab.setOnClickListener { view ->
            itemNumber = tvInventoryItemCode.text.toString()
            baseQuantity = etInventoryQuantity.text.toString().toInt()

            var newInventory = Inventory()
            /*newInventory.itemId = itemNumber.toInt()
            newInventory.quantity = baseQuantity
            newInventory.id = UUID.randomUUID()
            newInventory.userId = 99
            newInventory.date = LocalDateTime.now()*/
            newInventory.setId(UUID.randomUUID())
            newInventory.setItemId(itemNumber.toInt())
            newInventory.setQuantity(baseQuantity)
            newInventory.setUserId(99)
            newInventory.setDate(LocalDateTime.now())

            fun sendIntent(){
                val activityIntent = Intent(this, MainActivity::class.java)
                startActivity(activityIntent)
            }

            var inventoryService: InventoryService = ServiceBuilder.builderService(InventoryService::class.java)
            var createRequest: Call<Inventory>
            createRequest = inventoryService.createInventory(newInventory)

            createRequest.enqueue(object: Callback<Inventory>{
                override fun onFailure(call: Call<Inventory>, t: Throwable) {
                    Snackbar.make(view, "Failed to add inventory.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }

                override fun onResponse(call: Call<Inventory>, response: Response<Inventory>) {
                    Snackbar.make(view, "Inventory created.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()

                    sendIntent()
                }
            })
        }

        itemNumber = intent.getStringExtra("ADDED_ITEM_NUMBER")
        tvInventoryItemCode.setText(itemNumber)
    }

}
