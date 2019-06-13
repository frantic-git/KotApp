package ru.frantic.kotapp

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class SecondActivity : AppCompatActivity(),View.OnClickListener {

    lateinit var btnRed : Button
    lateinit var btnBlue : Button
    lateinit var btnGreen : Button

    val tag:String = "frantic_log"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        btnBlue = findViewById(R.id.btnBlue)
        btnGreen = findViewById(R.id.btnGreen)
        btnRed = findViewById(R.id.btnRed)

        btnRed.setOnClickListener(this)
        btnBlue.setOnClickListener(this)
        btnGreen.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v == null)return
        intent = Intent()
        when(v.id){
            R.id.btnRed ->{
                Log.d(tag, "push Red")
                intent.putExtra("color", Color.RED)
            }
            R.id.btnBlue ->{
                Log.d(tag, "push Blue")
                intent.putExtra("color", Color.BLUE)
            }
            R.id.btnGreen ->{
                Log.d(tag, "push Red")
                intent.putExtra("color", Color.GREEN)
            }
        }
        setResult(Activity.RESULT_OK,intent)
        finish()
    }
}
