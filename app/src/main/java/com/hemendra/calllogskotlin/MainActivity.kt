package com.hemendra.calllogskotlin

import android.Manifest
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.SimpleCursorAdapter
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var staus = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CALL_LOG)
        if(staus == PackageManager.PERMISSION_GRANTED)
        {
            readcallogs()
        }
        else
        {
           ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CALL_LOG),123)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            readcallogs()
        }
    }
    private fun readcallogs() {
      var  resolver:ContentResolver = contentResolver
      var cp_uri=ContactsContract.CommonDataKinds.Phone.CONTENT_URI
      var cursor:Cursor?=resolver.query(cp_uri,null,null,null,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        var colummnarray = arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone.CONTACT_LAST_UPDATED_TIMESTAMP)
        var txtviewArray = intArrayOf(R.id.txt_name,R.id.txt_phone,R.id.txt_time)

        var cAdapter = SimpleCursorAdapter(this,R.layout.list_item,cursor,colummnarray,txtviewArray,0)
        lv_calllogs.adapter = cAdapter



    }
}