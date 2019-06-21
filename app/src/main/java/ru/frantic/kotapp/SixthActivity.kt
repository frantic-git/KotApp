package ru.frantic.kotapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter

class SixthActivity : AppCompatActivity() {

    private val ATTRIBUTE_NAME_TEXT = "text"
    private val ATTRIBUTE_NAME_CHECKED = "checked"
    private val ATTRIBUTE_NAME_IMAGE = "image"

    lateinit var lvSimple:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sixth)

        val texts = arrayOf("Повешенный","Хромой","Костоглот")
        val checked = booleanArrayOf(true, false, false, true, false)
        val img = R.mipmap.ic_launcher

        val data = ArrayList<Map<String, Any>>(texts.size)

        for(i:Int in texts.indices){
            val m = HashMap<String, Any>()
            m.put(ATTRIBUTE_NAME_TEXT, texts[i])
            m.put(ATTRIBUTE_NAME_CHECKED, checked[i])
            m.put(ATTRIBUTE_NAME_IMAGE, img)
            data.add(m)
        }

        // массив имен атрибутов, из которых будут читаться данные
        val from = arrayOf(ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_CHECKED,
            ATTRIBUTE_NAME_IMAGE)

        val to = intArrayOf(R.id.tvText, R.id.cbChecked, R.id.ivImg )

        val sAdapter = SimpleAdapter(this, data, R.layout.item_simple_adapter, from, to)

        lvSimple = findViewById(R.id.lvSimple)
        lvSimple.adapter = sAdapter

    }

    //inner class FirstSimpleAdapter(context: Context,,resource:Int,from:Array<String>,to:IntArray):SimpleAdapter(context,data,resource,from,to){

    //val data:List<Map<String, String>>()

    /*
    context:Context,data:List<? extends Map<String, ?>>

        public MySimpleAdapter(Context context,
        List<? extends Map<String, ?>> data, int resource,
        String[] from, int[] to) {
            super(context, data, resource, from, to);
        }
    */

}
