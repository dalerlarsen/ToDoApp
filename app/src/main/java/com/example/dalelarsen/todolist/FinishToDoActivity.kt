package com.example.dalelarsen.todolist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import io.realm.Realm
import kotlinx.android.synthetic.main.content_main.*

class FinishToDoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_to_do)

        val toDoItemId = intent.getStringExtra("SELECTED_TODO_ID")

        title = "Complete ToDo"

        println(toDoItemId)

        val realm = Realm.getDefaultInstance()
        val toDoItem = realm.where(ToDoItem::class.java).equalTo("id", toDoItemId).findFirst()
        val textView = findViewById<TextView>(R.id.toDoName)
        textView.text = toDoItem?.name
        val completeButton = findViewById<Button>(R.id.completeButton)

        completeButton.setOnClickListener {
            realm.beginTransaction()
            toDoItem?.deleteFromRealm()
            realm.commitTransaction()
            finish()
        }
    }
}
