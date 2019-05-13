package com.example.inventoryandroidapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import android.widget.Adapter
import androidx.appcompat.app.AppCompatActivity;
import com.example.inventoryandroidapp.models.Category
import com.example.inventoryandroidapp.models.Item
import com.example.inventoryandroidapp.services.CategoryService
import com.example.inventoryandroidapp.services.ItemService
import com.example.inventoryandroidapp.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_add_item.*
import kotlinx.android.synthetic.main.content_add_item.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddItemActivity : AppCompatActivity() {

    private var itemNumber = ""
    private var baseQuantity = 0
    private var description = ""
    private var category = ""
    private var name = ""
    private var baseQty = ""
    private lateinit var categoryArrayList: ArrayList<Category>
    private lateinit var categoryDescriptions: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        setSupportActionBar(toolbar)
        val mContext: Context = this

        val categorySpinner : Spinner = findViewById(R.id.spinnerCategory)

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.category_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            categorySpinner.adapter = adapter
        }

        var categoryService: CategoryService = ServiceBuilder.builderService(CategoryService::class.java)
        var categoryRequest: Call<List<Category>> = categoryService.getCategories()

        categoryRequest.enqueue(object : Callback<List<Category>>{
            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                tvError.text = "Could not retrieve categories."
            }

            override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {
                /*var categories: ArrayList<Call<List<Category>>> = ArrayList()
                categories.add(categoryRequest)

                var adapter: ArrayAdapter<Call<List<Category>>> = ArrayAdapter<Call<List<Category>>>(
                    mContext, android.R.layout.simple_spinner_dropdown_item,categories)
                categorySpinner.setAdapter(adapter)*/

                //Use this later
                /*var adapter: ArrayAdapter<Category> = ArrayAdapter(mContext,
                    android.R.layout.simple_spinner_dropdown_item,response.body())
                categorySpinner.setAdapter(adapter)*/
            }
        })

        fabAddItem.setOnClickListener { view ->
            baseQty = etBaseQuantity.text.toString()
            baseQuantity = baseQty.toInt()
            description = etDescription.text.toString()
            category = spinnerCategory.selectedItem.toString()
            name = etName.text.toString()

            var newItem = Item()
            newItem.setId(itemNumber.toInt())
            newItem.setBaseQty(baseQuantity)
            newItem.setDescription(description)
            newItem.setCategoryId(category)
            newItem.setName(name)

            fun sendIntent(){
                val activityIntent = Intent(this, AddInventoryActivity::class.java)
                activityIntent.putExtra("ADDED_ITEM_NUMBER", tvInventoryItemCode.text.toString())
                startActivity(activityIntent)
            }

            var itemService: ItemService = ServiceBuilder.builderService(ItemService::class.java)
            var createRequest: Call<Item>
            createRequest = itemService.createItem(newItem)

            createRequest.enqueue(object: Callback<Item>{
                override fun onFailure(call: Call<Item>, t: Throwable) {
                    Toast.makeText(mContext, "Failed to create item", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Item>, response: Response<Item>) {
                    sendIntent()
                }
            })
        }

        itemNumber = intent.getStringExtra("ITEM_NUMBER")
        tvInventoryItemCode.setText(itemNumber)
    }

}
