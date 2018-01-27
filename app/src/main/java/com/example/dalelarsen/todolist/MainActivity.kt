package com.example.dalelarsen.todolist

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import io.realm.Realm
import io.realm.RealmQuery

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            var addIntent = Intent(this, AddToDoActivity::class.java)
            startActivity(addIntent)
        }

//        // Realm Fun
//        var myDog = Dog()
//        myDog.name = "Fido"
//        myDog.age = 8
//
//        val realm = Realm.getDefaultInstance()
//        realm.beginTransaction()
//        realm.copyToRealm(myDog)
//        realm.commitTransaction()
//
//        val query = realm.where(Dog::class.java)
//        val results = query.findAll()
//        for (dog in results) {
//            println(dog.name)
//        }
    }

    override fun onResume() {
        super.onResume()
        val realm = Realm.getDefaultInstance()

        val query = realm.where(ToDoItem::class.java)
        val results = query.findAll()

        val listView = findViewById<ListView>(R.id.todoItemListView)
        listView.setOnItemClickListener { adapterView, view, i, l ->
            val selectedToDo = results[i]

            val intent = Intent(this, FinishToDoActivity::class.java)
            intent.putExtra("SELECTED_TODO_ID", selectedToDo?.getId())
            startActivity(intent)
        }
        
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, results)
        listView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
