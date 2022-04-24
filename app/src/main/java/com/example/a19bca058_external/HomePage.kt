package com.example.a19bca058_external

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home_page.*

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        btnInsertFruit.setOnClickListener {
            if(edtFruitName.text.toString().equals("")||edtFruitPrice.text.toString().equals("")||edtqtyFruit.text.toString().equals(""))
            {
                Toast.makeText(this,"Enter Data Accurately",Toast.LENGTH_LONG).show()
            }
            else
            {
                var fr = Fruit(edtFruitName.text.toString(),edtFruitPrice.text.toString().toDouble(),edtqtyFruit.text.toString().toInt())
                var db = DBHelper(this)
                var res = db.insertFruit(fr)
                if(res>0)
                {
                    Toast.makeText(this,"Fruit Inserted Successfully",Toast.LENGTH_LONG).show()
                    edtFruitName.setText("")
                    edtFruitPrice.setText("")
                    edtqtyFruit.setText("")
                }
                else
                {
                    Toast.makeText(this,"Facing Problems!",Toast.LENGTH_LONG).show()
                }
            }
        }

        btnViewAllFruit.setOnClickListener {
            var intent = Intent(this,ViewAllFruits::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mymenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId
        if(id.equals(R.id.menuLogout))
        {
            var sp: SharedPreferences = getSharedPreferences("MyPref", MODE_PRIVATE)
            var prefedit = sp.edit()
            prefedit.clear()
            prefedit.commit()
            var intent = Intent(this,LoginPage::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}