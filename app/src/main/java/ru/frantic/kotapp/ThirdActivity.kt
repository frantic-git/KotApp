package ru.frantic.kotapp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button

class ThirdActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var btnLeft:Button
    lateinit var btnCenter:Button
    lateinit var btnRight:Button

    val tag:String = "frantic_log"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        btnLeft = findViewById(R.id.btnLeft)
        btnCenter = findViewById(R.id.btnCenter)
        btnRight = findViewById(R.id.btnRight)

        btnLeft.setOnClickListener(this)
        btnCenter.setOnClickListener(this)
        btnRight.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v == null)return
        intent = Intent()
        when(v.id){
            R.id.btnLeft ->{
                Log.d(tag, "push button Left")
                intent.putExtra("align", Gravity.LEFT)
            }
            R.id.btnCenter ->{
                Log.d(tag, "push button Center")
                intent.putExtra("align", Gravity.CENTER)
            }
            R.id.btnRight ->{
                Log.d(tag, "push button Right")
                intent.putExtra("align", Gravity.RIGHT)
            }
        }
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
