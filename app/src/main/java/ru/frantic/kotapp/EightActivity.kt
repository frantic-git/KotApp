package ru.frantic.kotapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.SimpleExpandableListAdapter
import java.util.HashMap

class EightActivity : AppCompatActivity() {

    private val tag = "frantic_log"
    private val CM_DELETE_ID = 1
    private val ATTRIBUTE_NAME_TEXT = "text"
    private val ATTRIBUTE_NAME_IMAGE = "image"

    lateinit var lvSimple:ListView
    lateinit var sAdapter:SimpleAdapter
    lateinit var data:ArrayList<Map<String, *>>

    var m = HashMap<String, Any>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eight)

        data = ArrayList()
        for(i in 1..4){
            //val m = HashMap<String, Any>()
            m.put(ATTRIBUTE_NAME_TEXT, "sometext $i")
            m.put(ATTRIBUTE_NAME_IMAGE, R.mipmap.ic_launcher)
            data.add(m)
        }

        val from = arrayOf(ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_IMAGE )
        val to = intArrayOf(R.id.tvText, R.id.ivImg)

        sAdapter = SimpleAdapter(this, data, R.layout.item_eight,from,to)

        lvSimple = findViewById(R.id.lvSimple)
        lvSimple.adapter = sAdapter
        registerForContextMenu(lvSimple)
    }

    fun onButtonClick(v:View){
        Log.d(tag,"add row")
        //val m = HashMap<String, Any>()
        m.put(ATTRIBUTE_NAME_TEXT, "sometext ${data.size + 1}")
        m.put(ATTRIBUTE_NAME_IMAGE, R.mipmap.ic_launcher)
        data.add(m)
        sAdapter.notifyDataSetChanged()
    }

    override fun onCreateContextMenu(menu:ContextMenu, v:View, menuInfo: ContextMenu.ContextMenuInfo){
        super.onCreateContextMenu(menu, v, menuInfo)
        Log.d(tag, "OnCreateContextMenu")
        menu.add(0, CM_DELETE_ID,0,"Delete row")
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == CM_DELETE_ID){
            val acm:AdapterView.AdapterContextMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
            data.removeAt(acm.position)
            sAdapter.notifyDataSetChanged()
            return true
        }
        return super.onContextItemSelected(item)
    }

}
