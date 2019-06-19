package ru.frantic.kotapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import android.widget.SimpleExpandableListAdapter
import android.widget.TextView

class FourthActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var elvMain:ExpandableListView
    lateinit var adapterHelper: AdapterHelper
    lateinit var adapter: SimpleExpandableListAdapter
    lateinit var tvInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)

        tvInfo = findViewById(R.id.tvInfo)

        adapterHelper = AdapterHelper(this)
        adapter = adapterHelper.getInitAdapter()

        elvMain = findViewById(R.id.elvMain)
        elvMain.setAdapter(adapter)

        elvMain.setOnGroupClickListener()

        /*
       // нажатие на группу
        elvMain.setOnGroupClickListener(new OnGroupClickListener() {
      public boolean onGroupClick(ExpandableListView parent, View v,
          int groupPosition, long id) {
        Log.d(LOG_TAG, "onGroupClick groupPosition = " + groupPosition +
                " id = " + id);
        // блокируем дальнейшую обработку события для группы с позицией 1
        if (groupPosition == 1) return true;

        return false;
      }
    });

        // сворачивание группы
        elvMain.setOnGroupCollapseListener(new OnGroupCollapseListener() {
      public void onGroupCollapse(int groupPosition) {
        Log.d(LOG_TAG, "onGroupCollapse groupPosition = " + groupPosition);
        tvInfo.setText("Свернули " + ah.getGroupText(groupPosition));
      }
    });

        // разворачивание группы
        elvMain.setOnGroupExpandListener(new OnGroupExpandListener() {
      public void onGroupExpand(int groupPosition) {
        Log.d(LOG_TAG, "onGroupExpand groupPosition = " + groupPosition);
        tvInfo.setText("Развернули " + ah.getGroupText(groupPosition));
      }
    });

        // разворачиваем группу с позицией 2
        elvMain.expandGroup(2);

         */

    }

    override fun onClick(v: View?) {

    }
}
