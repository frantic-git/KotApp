package ru.frantic.kotapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.SimpleAdapter

class SeventhActivity : AppCompatActivity(), View.OnClickListener {


    private val ATTRIBUTE_NAME_TEXT = "text";
    private val ATTRIBUTE_NAME_PB = "pb";
    private val ATTRIBUTE_NAME_LL = "ll";

    lateinit var lvSimple:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seventh)

        lvSimple = findViewById(R.id.lvSimple)

        val load = intArrayOf(41, 48, 22, 35, 30, 67, 51, 88)
        val data = ArrayList<Map<String, *>>(load.size)

        for(i:Int in load.indices){
            val m = HashMap<String, Any>()
            m[ATTRIBUTE_NAME_TEXT] = "Day ${i+1} . Load: ${load[i]} %"
            m[ATTRIBUTE_NAME_LL] = load[i]
            m[ATTRIBUTE_NAME_PB] = load[i]
            data.add(m)
        }
        val from = arrayOf(ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_LL, ATTRIBUTE_NAME_PB)
        val to = intArrayOf(R.id.tvLoad, R.id.pbLoad, R.id.llLoad)

        val simpleAdapter:SimpleAdapter = SimpleAdapter(this,data,
            R.layout.item_progressbar_simple_adapter,from,to)
        simpleAdapter.viewBinder = KotViewBinder()
        lvSimple.adapter = simpleAdapter
    }

    override fun onClick(v: View?) {

    }

    inner class KotViewBinder:SimpleAdapter.ViewBinder{

        var red = resources.getColor(R.color.Red)
        var orange = resources.getColor(R.color.Orange)
        var green = resources.getColor(R.color.Green)

        override fun setViewValue(view: View?, data: Any?, textRepresentation: String?): Boolean {

            var i:Int
            when(view?.id){
                R.id.llLoad ->{
                    i = data as Int //.toInt()
                    when{
                        i < 40 -> view.setBackgroundColor(green)
                        i < 70 -> view.setBackgroundColor(orange)
                        else -> view.setBackgroundColor(red)
                    }
                    return true
                }
                R.id.pbLoad ->{
                    i = data as Int
                    (view as ProgressBar).progress = i
                    return true
                }
            }

            return false
        }
    }
}
