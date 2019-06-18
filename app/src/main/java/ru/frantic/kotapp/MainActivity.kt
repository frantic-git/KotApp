package ru.frantic.kotapp

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Color
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity(),View.OnClickListener {

    lateinit var btnColor : Button
    lateinit var btnAlign: Button
    lateinit var btnWeb: Button
    lateinit var btnMap: Button
    lateinit var btnCall: Button
    lateinit var btnSave: Button
    lateinit var btnLoad: Button
    lateinit var btnAdd: Button
    lateinit var btnRead: Button
    lateinit var btnClear: Button
    lateinit var btnUpdate: Button
    lateinit var btnDelete: Button

    lateinit var editText: EditText
    lateinit var etName: EditText
    lateinit var etEmail: EditText

    lateinit var sPref: SharedPreferences
    lateinit var dbHelper: DBHelper

    val tag : String = "frantic_log"
    private val DB_VERSION : Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.etText)
        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)

        btnColor = findViewById(R.id.btnColor)
        btnAlign = findViewById(R.id.btnAlign)
        btnWeb = findViewById(R.id.btnWeb)
        btnMap = findViewById(R.id.btnMap)
        btnCall = findViewById(R.id.btnCall)
        btnSave = findViewById(R.id.btnSave)
        btnLoad = findViewById(R.id.btnLoad)
        btnAdd = findViewById(R.id.btnAdd)
        btnRead = findViewById(R.id.btnRead)
        btnClear = findViewById(R.id.btnClear)
        btnDelete = findViewById(R.id.btnDelete)
        btnUpdate = findViewById(R.id.btnUpdate)

        btnColor.setOnClickListener(this)
        btnAlign.setOnClickListener(this)
        btnWeb.setOnClickListener(this)
        btnMap.setOnClickListener(this)
        btnCall.setOnClickListener(this)
        btnSave.setOnClickListener(this)
        btnLoad.setOnClickListener(this)
        btnAdd.setOnClickListener(this)
        btnRead.setOnClickListener(this)
        btnClear.setOnClickListener(this)
        btnUpdate.setOnClickListener(this)
        btnDelete.setOnClickListener(this)

        val ltInflater = layoutInflater
        val view = ltInflater.inflate(R.layout.text,null, false)
        val lp = view.layoutParams

        Log.d(tag, "Class of view: " + view.javaClass.toString())
        Log.d(tag, "LayoutParams of view is null: " + (lp == null))
        Log.d(tag, "Text of view: " + (view as TextView).text)

        val linLayout:LinearLayout = findViewById(R.id.linLayout)
        linLayout.addView(view)
        Log.d(tag, "Class of view: " + view.javaClass.toString());

        dbHelper = DBHelper.getInstance(this, DB_VERSION)
        val db = dbHelper.writableDatabase

        Log.d(tag, " --- Staff db v." + db.version + " --- ");
        writeStaff(db);
        dbHelper.close();

    }

    private fun writeStaff(db : SQLiteDatabase){
        val c = db.rawQuery("select * from person", null)
        logCursor(c, "Table person")
        c.close()
    }

    private fun logCursor(c:Cursor,title:String){

        Log.d(tag, "$title. ${c.count} rows ")
        val sb = StringBuilder()

        while(c.moveToNext()){
            sb.setLength(0)
            for (cn in c.columnNames) {
                sb.append("$cn = ${c.getString(c.getColumnIndex(cn))};")
            }
            Log.d(tag, sb.toString())
        }
    }

    override fun onClick(v: View?) {
        if(v == null)return
        when (v.id){
            R.id.btnColor -> {
                Log.d(tag, "push Color")
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("DB_VERSION", DB_VERSION)
                startActivityForResult(intent, 1)
            }
            R.id.btnAlign -> {
                Log.d(tag, "push Align")
                val intent = Intent(this, ThirdActivity::class.java)
                intent.putExtra("DB_VERSION", DB_VERSION)
                startActivityForResult(intent, 2);
            }
            R.id.btnWeb ->{
                intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://ya.ru"))
                startActivity(intent)
            }
            R.id.btnMap ->{
                intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:55.754283,37.62002"))
                startActivity(intent)
            }
            R.id.btnCall ->{
                intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:12345"))
            }
            R.id.btnSave ->{
                savePref()
            }
            R.id.btnLoad ->{
                loadPref()
            }
            R.id.btnAdd ->{
                add()
            }
            R.id.btnRead ->{
                read()
            }
            R.id.btnClear ->{
                clear()
            }
            R.id.btnUpdate ->{
                update()
            }
            R.id.btnDelete ->{
                delete()
            }
        }
    }

    private fun update(){
        Log.d(tag, "--- update in emailList email ---")
        val db = dbHelper.writableDatabase
        val cv = ContentValues()
        val name = etName.text.toString()
        val email = etEmail.text.toString()

        cv.put("email",email)
        val rowId = db.update("emailList", cv,"name = ?", arrayOf(name))
        Log.d(tag, "row updated, ID = $rowId")
        db.close()
    }

    private fun delete(){
        Log.d(tag, "--- delete in emailList ---")
        val db = dbHelper.writableDatabase
        val name = etName.text.toString()

        val rowId = db.delete("emailList","name = \"$name\"" , null)
        Log.d(tag, "row deleted, ID = $rowId")
        db.close()
    }

    private fun clear(){
        Log.d(tag, "--- Clear emailList: ---")
        val db = dbHelper.writableDatabase
        val clearCount = db.delete("emailList", null, null)
        Log.d(tag, "deleted rows count = $clearCount")
        db.close()
    }

    private fun add(){
        Log.d(tag, " --- insert in emailList table ---")

        val db = dbHelper.writableDatabase
        val cv = ContentValues()
        val name = etName.text.toString()
        val email = etEmail.text.toString()

        cv.put("name",name)
        cv.put("email",email)

        val rowID = db.insert("emailList", null, cv)
        Log.d(tag, "row inserted, ID = ${rowID}")
        dbHelper.close()
    }

    private fun read(){
        thread(start = true){

            val db = dbHelper.readableDatabase
            var cur = db.query("emailList",null, null, null, null, null, null)
            var colId = cur.getColumnIndex("id")
            var nameColId = cur.getColumnIndex("name")
            val emailColId = cur.getColumnIndex("email")
            if(cur.count == 0)Log.d(tag,"emailList is empty")
            while (cur.moveToNext()) {
                Log.d(tag, "row id ${cur.getInt(colId)}" +
                        "  name: ${cur.getString(nameColId)}" +
                        " email: ${cur.getString(emailColId)} ")
            }
            cur.close()

            Log.d(tag, "Table person")
            cur = db.query("person", null, null, null, null, null, null)
            colId = cur.getColumnIndex("id")
            nameColId = cur.getColumnIndex("name")
            val positionColId = cur.getColumnIndex("position")

            if(cur.count == 0)Log.d(tag, "person table is empty")
            while(cur.moveToNext()){
                Log.d(tag, "row id ${cur.getInt(colId)}" +
                        "  name: ${cur.getString(nameColId)}" +
                        " position: ${cur.getString(positionColId)} ")
            }
            cur.close()

            dbHelper.close()
        }
    }

    private fun savePref(){
        sPref = getPreferences(Context.MODE_PRIVATE)
        val ed = sPref.edit()
        ed.putString(tag,editText.text.toString())
        ed.apply()
        Toast.makeText(this, "Save complete", Toast.LENGTH_SHORT).show()
    }

    private fun loadPref(){
        sPref = getPreferences(Context.MODE_PRIVATE)
        editText.setText(sPref.getString(tag,""))
        Toast.makeText(this, "Load complete", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode!= Activity.RESULT_OK)return
        if(data == null)return
        when(requestCode){
            1 ->{
                val color = data.getIntExtra("color", Color.WHITE)
                tvText.setTextColor(color)
            }
            2 ->{
                val align = data.getIntExtra("align", Gravity.START)
                tvText.gravity = align
            }
        }
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

    /* //Реализация внутреннего класса
    inner class DBHelper(context: Context) : SQLiteOpenHelper(context, "KotDB", null, DB_VERSION) {

        override fun onCreate(db: SQLiteDatabase?) {
            Log.d(this@MainActivity.tag,"--------------- CREATE DATABASE -------------")

            db?.beginTransaction()

            db?.execSQL("create table emailList ("
                    + "id integer primary key autoincrement,"
                    + "name text,"
                    + "email text" + ");")

            val personName = arrayOf("Шепот", "Хромой", "Душелов", "Меняющий", "Костоправ")
            val personPositions = arrayOf("хранитель","взятый", "сержант", "лейтенант", "хирург")

            val cv = ContentValues()

            // создаем таблицу людей
            db?.execSQL("create table person ("
                    + "id integer primary key autoincrement,"
                    + "name text, position text);")

            for( i in personName.indices) {
                cv.clear()
                cv.put("name", personName[i])
                cv.put("position", personPositions[i])
                db?.insert("person",null,cv)
            }

            db?.setTransactionSuccessful()
            db?.endTransaction()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            Log.d(tag, " --- onUpgrade database from " + oldVersion
                    + " to " + newVersion + " version --- ");

            if (oldVersion == 1 && newVersion == 2) {
                val personName = arrayOf("Шепот", "Хромой", "Душелов", "Меняющий", "Костоправ")
                val personPositions = arrayOf("хранитель","взятый", "сержант", "лейтенант", "хирург")

                val cv = ContentValues()

                // создаем таблицу людей
                db?.execSQL("create table person ("
                        + "id integer primary key autoincrement,"
                        + "name text, position text);")

                for( i in personName.indices) {
                    cv.clear()
                    cv.put("name", personName[i])
                    cv.put("position", personPositions[i])
                    db?.insert("person",null,cv)
                }
            }
        }
    }
    */

}
