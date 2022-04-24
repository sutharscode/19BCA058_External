package com.example.a19bca058_external

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_view_all_fruits.*
import kotlinx.android.synthetic.main.customdialog.*

class ViewAllFruits : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all_fruits)
        RefreshRecycler()
    }
    fun RefreshRecycler()
    {
        var arr = ArrayList<Fruit>()
        var db = DBHelper(this)
        arr = db.GetAllFruits()
        var adapt = FruitAdapter(this,arr)
        MyRecycleOP.adapter = adapt
    }
    fun Update(position:Int)
    {
        var db=DBHelper(this)
        var arr:ArrayList<Fruit> = db.GetAllFruits()

        var dialog= Dialog(this)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.customdialog)

        // Full Screen Code::
        //___________THIS CODE IS OPTIONAL TO EXECUTE_____________
        // FROM Next line from here to next five Lines.....
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.CENTER
        dialog.window!!.attributes = lp
        dialog.show()
        dialog.setCancelable(false)

        dialog.txtupId.text = arr[position].F_Id.toString()
        dialog.edtUpdateFname.setText(arr[position].F_Name)
        dialog.edtUpdateFPrice.setText(arr[position].F_Price.toString())
        dialog.edtUpdateFQuantity.setText(arr[position].F_Qty.toString())

        dialog.btnFinallyUpdate.setOnClickListener {
            var id=dialog.txtupId.text.toString().toInt()
            var name=dialog.edtUpdateFname.text.toString()
            var price=dialog.edtUpdateFPrice.text.toString()
            var quantity=dialog.edtUpdateFQuantity.text.toString()
            var f=Fruit(id,name,price.toDouble(),quantity.toInt())
            db.update(f)
            dialog.dismiss()
            RefreshRecycler()
        }
    }
    fun Delete(position:Int)
    {
        Toast.makeText(applicationContext,"im here", Toast.LENGTH_LONG).show()
        var db=DBHelper(this)
        var arr:ArrayList<Fruit> = db.GetAllFruits()
        var fruit=arr.get(position)
        Toast.makeText(applicationContext,"${fruit.F_Id}", Toast.LENGTH_LONG).show()
        db.Delete(fruit.F_Id)
        RefreshRecycler()
    }
}