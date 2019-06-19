package ru.frantic.kotapp

import android.content.Context
import android.widget.SimpleExpandableListAdapter

class AdapterHelper(_ctx:Context) {

    private val ATTR_GROUP_NAME:String = "Характеристика"
    private val ATTR_SKILL_NAME:String = "Навык"

    val groups = arrayOf("Физические","Ментальные","Социальные")
    val skillsPhysic = arrayOf("Атлетика","Телосложение","Бой", "Скрытность", "Вождение", "Стрельба")
    val skillsMental = arrayOf("Знания", "Внимательность", "Расследование", "Ремесла", "Взлом", "Ресурсы")
    val skillsSocial = arrayOf("Контакты", "Эмпатия", "Обаяние", "Обман", "Провокация", "Воля")
    val ctx:Context = _ctx

    lateinit var groupData:ArrayList<Map<String, String>>
    lateinit var childDataItem:ArrayList<Map<String, String>>
    lateinit var childData:ArrayList<ArrayList<Map<String,String>>>
    lateinit var m:HashMap<String, String>
    lateinit var adapter:SimpleExpandableListAdapter

    fun getInitAdapter():SimpleExpandableListAdapter{

        groupData = ArrayList()
        for(group in groups){
            m = HashMap()
            m.put(ATTR_GROUP_NAME, group)
            groupData.add(m)
        }

        childData = ArrayList()

        childDataItem = ArrayList()
        for(skill in skillsPhysic){
            m = HashMap()
            m.put(ATTR_SKILL_NAME, skill)
            childDataItem.add(m)
        }
        childData.add(childDataItem)

        childDataItem = ArrayList()
        for(skill in skillsMental){
            m = HashMap()
            m.put(ATTR_SKILL_NAME, skill)
            childDataItem.add(m)
        }
        childData.add(childDataItem)

        childDataItem = ArrayList()
        for(skill in skillsSocial){
            m = HashMap()
            m.put(ATTR_SKILL_NAME, skill)
            childDataItem.add(m)
        }
        childData.add(childDataItem)

        val groupFrom = arrayOf(ATTR_GROUP_NAME)
        val groupTo = intArrayOf(android.R.id.text1)

        val childFrom = arrayOf(ATTR_SKILL_NAME)
        val childTo = intArrayOf(android.R.id.text1)

        adapter = SimpleExpandableListAdapter(
            ctx,
            groupData,
            android.R.layout.simple_expandable_list_item_1,
            groupFrom,
            groupTo,
            childData,
            android.R.layout.simple_list_item_1,
            childFrom,
            childTo
        )

        return adapter
    }

    fun getGroupText(groupPos:Int): String? {
        return (adapter.getGroup(groupPos) as Map<String, String>).get(ATTR_GROUP_NAME)
    }

    fun getChildText(groupPos: Int, childPos: Int):String?{
        return (adapter.getChild(groupPos, childPos) as Map<String, String>).get(ATTR_SKILL_NAME)
    }

    fun getGroupChildText(groupPos: Int, childPos:Int):String?{
        return "${getGroupText(groupPos)} ${getChildText(groupPos, childPos)}"
    }
}