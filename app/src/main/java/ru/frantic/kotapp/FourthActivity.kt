package ru.frantic.kotapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ExpandableListView
import android.widget.SimpleExpandableListAdapter
import android.widget.TextView

class FourthActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var elvMain:ExpandableListView
    lateinit var adapterHelper: AdapterHelper
    lateinit var adapter: SimpleExpandableListAdapter
    lateinit var tvInfo: TextView

    private val tag = "frantic_log"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)

        tvInfo = findViewById(R.id.tvInfo)

        adapterHelper = AdapterHelper(this)
        adapter = adapterHelper.getInitAdapter()

        elvMain = findViewById(R.id.elvMain)
        elvMain.setAdapter(adapter)

        //нажатие на группу
        elvMain.setOnGroupClickListener(object: ExpandableListView.OnGroupClickListener{
            override fun onGroupClick(parent: ExpandableListView?, v: View?, groupPosition: Int, id: Long): Boolean {
                Log.d(tag, "onGroupClick groupPosition = $groupPosition id = $id")
                if(groupPosition == 1)return true
                return false
            }
        })

        //сворачивание группы
        elvMain.setOnGroupCollapseListener(object: ExpandableListView.OnGroupCollapseListener{
            override fun onGroupCollapse(groupPosition: Int) {
                Log.d(tag, "onGroupCollapse groupPosition = $groupPosition")
                tvInfo.text = "Свернули ${adapterHelper.getGroupText(groupPosition)}"
            }
        })

        // разворачивание группы
        elvMain.setOnGroupExpandListener{
            Log.d(tag, "onGroupExpand groupPosition = $it")
            tvInfo.text = "Развернули ${adapterHelper.getGroupText(it)}"
        }

        //разворачиваем группу с позицией 2
        elvMain.expandGroup(2)
    }

    override fun onClick(v: View?) {

    }
}
