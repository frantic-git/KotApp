package ru.frantic.kotapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

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



        /*

    // массив имен атрибутов, из которых будут читаться данные
    String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_VALUE,
        ATTRIBUTE_NAME_IMAGE };
    // массив ID View-компонентов, в которые будут вставлять данные
    int[] to = { R.id.tvText, R.id.tvValue, R.id.ivImg };

    // создаем адаптер
    MySimpleAdapter sAdapter = new MySimpleAdapter(this, data,
        R.layout.item, from, to);

    // определяем список и присваиваем ему адаптер
    lvSimple = (ListView) findViewById(R.id.lvSimple);
    lvSimple.setAdapter(sAdapter);
         */
    }
}
