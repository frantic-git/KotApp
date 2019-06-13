package ru.frantic.kotapp

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {

    lateinit var btnColor : Button
    lateinit var btnAlign: Button
    val tag : String = "frantic_log"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnColor = findViewById(R.id.btnColor)
        btnAlign = findViewById(R.id.btnAlign)

        btnColor.setOnClickListener(this)
        btnAlign.setOnClickListener(this)
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
}
