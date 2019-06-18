package ru.frantic.kotapp

import android.app.ActionBar
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import kotlin.concurrent.thread

class SecondActivity : AppCompatActivity(),View.OnClickListener {

    lateinit var btnRed : Button
    lateinit var btnBlue : Button
    lateinit var btnGreen : Button
    lateinit var ltInflater: LayoutInflater

    private val tag:String = "frantic_log"
    lateinit var dbHelper:DBHelper
    lateinit var linPersonList:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        btnBlue = findViewById(R.id.btnBlue)
        btnGreen = findViewById(R.id.btnGreen)
        btnRed = findViewById(R.id.btnRed)

        btnRed.setOnClickListener(this)
        btnBlue.setOnClickListener(this)
        btnGreen.setOnClickListener(this)

        val DB_VERSION:Int = intent.extras.getInt("DB_VERSION")

        ltInflater = layoutInflater
        linPersonList = findViewById(R.id.linPersonList)

        dbHelper = DBHelper.getInstance(this, DB_VERSION)
        readPerson()
    }

    private fun readPerson(){
        thread(start = true){
            val db = dbHelper.readableDatabase

            val colors = arrayOf(Color.parseColor("#559966CC"), Color.parseColor("#55336699"))

            Log.d(tag, "Table person")
            val cur = db.query("person", null, null, null, null, null, null)
            val colId = cur.getColumnIndex("id")
            val nameColId = cur.getColumnIndex("name")
            val positionColId = cur.getColumnIndex("position")

            if(cur.count == 0)Log.d(tag, "person table is empty")
            while(cur.moveToNext()){
                Log.d(tag, "row id ${cur.getInt(colId)}" +
                        "  name: ${cur.getString(nameColId)}" +
                        " position: ${cur.getString(positionColId)} ")

                //----------------
                val item:View = ltInflater.inflate(R.layout.person_item, linPersonList, false)

                val tvName:TextView = item.findViewById(R.id.tvName)
                tvName.text = "name: ${cur.getString(nameColId)}"

                val tvPosition:TextView = item.findViewById(R.id.tvPosition)
                tvPosition.text = "position ${cur.getString(positionColId)}"

                item.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
                item.setBackgroundColor(colors[cur.getInt(colId) % 2])

                /*if (item.getParent() != null) {
                    (item.getParent() as ViewGroup).removeView(item)
                }*/

                linPersonList.addView(item)
                //----------------
            }
            cur.close()
        }
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
