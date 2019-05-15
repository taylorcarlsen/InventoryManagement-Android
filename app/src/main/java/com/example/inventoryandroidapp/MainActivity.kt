package com.example.inventoryandroidapp

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.SurfaceView
import android.widget.Button
import android.widget.TextView
import android.view.SurfaceHolder
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.inventoryandroidapp.models.Item
import com.example.inventoryandroidapp.services.ItemService
import com.example.inventoryandroidapp.services.ServiceBuilder
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import java.util.jar.Manifest
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var svBarcode: SurfaceView
    private lateinit var detector: BarcodeDetector
    private lateinit var cameraSource: CameraSource
    private lateinit var tvBarcode: TextView
    lateinit var stringResponse: String
    lateinit var idArray: ArrayList<String>
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        idArray = ArrayList()
        stringResponse = ""
        svBarcode = findViewById(R.id.svBarcode)
        tvBarcode = findViewById(R.id.tvBarcode)

        detector = BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.ALL_FORMATS).build()
        detector.setProcessor(object : Detector.Processor<Barcode>{
            override fun release(){}

            override fun receiveDetections(detections: Detector.Detections<Barcode>?){
                val barcodes = detections?.detectedItems
                if(barcodes!!.size()>0){
                    tvBarcode.post{
                        tvBarcode.text = barcodes.valueAt(0).displayValue
                    }
                }
            }
        })

        var itemService: ItemService = ServiceBuilder.builderService(ItemService::class.java)
        var itemRequest: Call<List<Item>> = itemService.getItems()

        itemRequest.enqueue(object : Callback<List<Item>> {
            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                //tvError.text = "Could not retrieve categories."
            }

            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                response.body()?.forEach {
                    stringResponse = it.id.toString()
                    idArray.add(stringResponse)
                }
            }
        })

        fabAddItem.setOnClickListener { view ->

            if(idArray.contains(tvBarcode.text)){
                val activityIntent = Intent(this, AddInventoryActivity::class.java)
                activityIntent.putExtra("ITEM_NUMBER", tvBarcode.text)
                activityIntent.putExtra("EMPLOYEE_ID",userId)
                    startActivity(activityIntent)
            }else {
                val activityIntent = Intent(this, AddItemActivity::class.java)
                activityIntent.putExtra("ITEM_NUMBER", tvBarcode.text)
                startActivity(activityIntent)
            }
        }

        cameraSource = CameraSource.Builder(this, detector).setRequestedPreviewSize(1024, 786)
            .setRequestedFps(25f).setAutoFocusEnabled(true).build()
        svBarcode.holder.addCallback(object : SurfaceHolder.Callback2{
            override fun surfaceRedrawNeeded(holder: SurfaceHolder?){}
            override fun surfaceChanged(holder:SurfaceHolder?,forat: Int, width: Int, height: Int){}
            override fun surfaceDestroyed(holder: SurfaceHolder?){
                cameraSource.stop()
            }

            override fun surfaceCreated(holder: SurfaceHolder?) {
                if(ContextCompat.checkSelfPermission(this@MainActivity,
                        android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                    cameraSource.start(holder)
                else ActivityCompat.requestPermissions(this@MainActivity,
                    arrayOf(android.Manifest.permission.CAMERA),123)
            }
        })
        userId = intent.getStringExtra("EMPLOYEE_ID")
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 123){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                cameraSource.start(svBarcode.holder)
            else Toast.makeText(this, "Scanner won't work without permission.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        detector.release()
        cameraSource.stop()
        cameraSource.release()
    }

    // Examples
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
