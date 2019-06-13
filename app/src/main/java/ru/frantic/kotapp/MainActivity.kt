package ru.frantic.kotapp

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URI

class MainActivity : AppCompatActivity(),View.OnClickListener {

    lateinit var btnColor : Button
    lateinit var btnAlign: Button
    lateinit var btnWeb: Button
    lateinit var btnMap: Button
    lateinit var btnCall: Button

    val tag : String = "frantic_log"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnColor = findViewById(R.id.btnColor)
        btnAlign = findViewById(R.id.btnAlign)
        btnWeb = findViewById(R.id.btnWeb)
        btnMap = findViewById(R.id.btnMap)
        btnCall = findViewById(R.id.btnCall)

        btnColor.setOnClickListener(this)
        btnAlign.setOnClickListener(this)
        btnWeb.setOnClickListener(this)
        btnMap.setOnClickListener(this)
        btnCall.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v == null)return
        when (v.id){
            R.id.btnColor -> {
                Log.d(tag, "push Color")
                val intent = Intent(this, SecondActivity::class.java)
                startActivityForResult(intent, 1)
            }
            R.id.btnAlign -> {
                Log.d(tag, "push Align")
                val intent = Intent(this, ThirdActivity::class.java)
                startActivityForResult(intent, 2);
            }
            R.id.btnWeb ->{
                intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com"))
                startActivity(intent)
            }
            R.id.btnMap ->{
                intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:55.754283,37.62002"))
                startActivity(intent)
            }
            R.id.btnCall ->{
                intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:12345"))
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode!= Activity.RESULT_OK)return
        if(data == null)return
        when(requestCode){
            1 ->{
                var color = data.getIntExtra("color", Color.WHITE)
                tvText.setTextColor(color)
            }
            2 ->{
                var align = data.getIntExtra("align", Gravity.LEFT)
                tvText.gravity = align
            }
        }
    }

    override fun onRestart() {
        Log.d(tag, "onResume")
        super.onRestart()
    }

    override fun onStart() {
        Log.d(tag,"onStart")
        super.onStart()
    }

    override fun onPause() {
        Log.d(tag, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(tag, "onStop")
        super.onStop()
    }

    override fun onResume() {
        Log.d(tag, "onResume")
        super.onResume()
    }

    override fun onDestroy() {
        Log.d(tag, "onDestroy")
        super.onDestroy()
    }

}
