package ru.frantic.kotapp

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView

class FifthActivity : AppCompatActivity() {

    private val ATTRIBUTE_NAME_TEXT = "text"
    private val ATTRIBUTE_NAME_VALUE = "value"
    private val ATTRIBUTE_NAME_IMAGE = "image"

    private val positive = android.R.drawable.arrow_up_float
    private val negative = android.R.drawable.arrow_down_float

    lateinit var lvSimple:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifth)

        val values = intArrayOf(8, 4, -3, 2, -5, 0, 3, -6, 1, -1)
        val data = ArrayList<Map<String, Any>>(values.size)

        var img:Int

        for(i:Int in values.indices){
            val m = HashMap<String, Any>()
            m.put(ATTRIBUTE_NAME_TEXT, "Day " + (i + 1))
            m.put(ATTRIBUTE_NAME_VALUE, values[i])
            if (values[i] == 0) img = 0; else
                img = if(values[i] > 0) positive else negative
            m.put(ATTRIBUTE_NAME_IMAGE, img);
            data.add(m);
        }

        val from = arrayOf(ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_VALUE,
            ATTRIBUTE_NAME_IMAGE)
        val to = intArrayOf(R.id.tvText, R.id.tvValue, R.id.ivImg)

        val sAdapter = KotSimpleAdapter(this, data, R.layout.item_simple_adapter_ext,from,to)

        lvSimple = findViewById(R.id.lvSimple)
        lvSimple.adapter = sAdapter

    }

    inner class KotSimpleAdapter(
        context: Context?,
        data:ArrayList<Map<String, Any>>?, // data: MutableList<out MutableMap<String, *>>?,
        resource: Int,
        from: Array<out String>?,
        to: IntArray?
    ) : SimpleAdapter(context, data, resource, from, to) {

        override fun setViewText(v:TextView, text:String){
            super.setViewText(v, text)
            if(v.id == R.id.tvValue){
                val i = Integer.parseInt(text)
                if(i<0)
                    v.setTextColor(Color.RED)
                else
                    v.setTextColor(Color.GREEN)
            }
        }

        override fun setViewImage(v: ImageView?, value: Int) {
            super.setViewImage(v, value)
            when (value) {
                negative -> v?.setBackgroundColor(Color.RED)
                positive -> v?.setBackgroundColor(Color.GREEN)
            }
        }
    }
}
