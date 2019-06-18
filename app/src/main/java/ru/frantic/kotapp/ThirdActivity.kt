package ru.frantic.kotapp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.*
import android.widget.AbsListView.CHOICE_MODE_MULTIPLE
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var btnLeft:Button
    lateinit var btnCenter:Button
    lateinit var btnRight:Button
    lateinit var btnOK:Button
    lateinit var names:Array<String>

    private val tag:String = "frantic_log"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        btnLeft = findViewById(R.id.btnLeft)
        btnCenter = findViewById(R.id.btnCenter)
        btnRight = findViewById(R.id.btnRight)
        btnOK = findViewById(R.id.btnOK)

        btnLeft.setOnClickListener(this)
        btnCenter.setOnClickListener(this)
        btnRight.setOnClickListener(this)
        btnOK.setOnClickListener(this)

        names = arrayOf("chromoi", "kostoprav", "logen")

        val lvMain:ListView = findViewById(R.id.lvMain)

        lvMain.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ////варианты представления списка
        //android.R.layout.simple_list_item_1
        //android.R.layout.simple_list_item_single_choice
        lvMain.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, names)

        lvMain.setOnItemClickListener(object: AdapterView.OnItemClickListener {
                override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Log.d(tag, "item clicked blat")
                }
            }
        )

    }

    override fun onClick(v: View?) {

        if (v == null) return
        intent = Intent()
        when (v.id) {
            R.id.btnLeft -> {
                Log.d(tag, "push button Left")
                intent.putExtra("align", Gravity.LEFT)
            }
            R.id.btnCenter -> {
                Log.d(tag, "push button Center")
                intent.putExtra("align", Gravity.CENTER)
            }
            R.id.btnRight -> {
                Log.d(tag, "push button Right")
                intent.putExtra("align", Gravity.RIGHT)
            }
            R.id.btnOK -> {
                Log.d(tag, "push button OK")

                val sbArray = lvMain.checkedItemPositions
                var i = 0
                while (i < sbArray.size()) {
                    val key = sbArray.keyAt(i)
                    if (sbArray.get(key)) Log.d(tag, names[key])
                    i++
                }
                return
            }
        }
        setResult(Activity.RESULT_OK, intent)
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
