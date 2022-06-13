package com.example.permissonx

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.permissionx.yuhong.PermissionCallback
import com.permissionx.yuhong.PermissionX
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val makeCallBtn = findViewById<Button>(R.id.makeCallBtn)
        val test = 1
        makeCallBtn.setOnClickListener{
            PermissionX.request(this,
            android.Manifest.permission.CALL_PHONE,callback = {
                        allGranted:Boolean,deniedList:List<String> -> Unit
                    if (allGranted){
                        call()
                    }else{
                        Toast.makeText(this,"You denied $deniedList",Toast.LENGTH_SHORT).show()
                    }
                })

        }
    }

    private fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        }catch (e: SecurityException){
            e.printStackTrace()
        }
    }
}