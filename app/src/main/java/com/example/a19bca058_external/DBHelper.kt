package com.example.a19bca058_external

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (var context: Context) : SQLiteOpenHelper(context, DB_NAME,null, DB_VER) {
    companion object{
        private val DB_NAME = "PracticeDB"
        private val TB_USER = "User"
        private val TB_FRUIT = "Fruit"
        private val DB_VER = 1

        private val ID_USER = "U_Id"
        private val PASSWORD_USER = "U_Password"
        private val EMAIL_USER = "U_Email"
        private val AGE_USER = "U_Age"

        private val ID_FR = "F_Id"
        private val NAME_FR = "F_Name"
        private val PRICE_FR = "F_Price"
        private val QTY_FR = "F_Qty"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        var sql1 = "CREATE TABLE $TB_USER ($ID_USER INTEGER PRIMARY KEY AUTOINCREMENT, $PASSWORD_USER TEXT, $EMAIL_USER TEXT, $AGE_USER INTEGER)"
        var sql2 = "CREATE TABLE $TB_FRUIT ($ID_FR INTEGER PRIMARY KEY AUTOINCREMENT, $NAME_FR TEXT, $PRICE_FR DOUBLE, $QTY_FR INTEGER)"

        p0?.execSQL(sql1)
        p0?.execSQL(sql2)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun insertUser(us : User): Long
    {
        var db = writableDatabase
        var cn = ContentValues()
        cn.put(EMAIL_USER,us.email)
        cn.put(AGE_USER,us.age)
        cn.put(PASSWORD_USER, us.password)

        var res = db.insert(TB_USER,null,cn)
        return res
        db.close()
    }
    fun getUser(uname:String): ArrayList<User>
    {
        var db = readableDatabase
        var sql = "Select * from $TB_USER where $EMAIL_USER = '$uname'"
        var arr = ArrayList<User>()
        var cursor = db.rawQuery(sql,null)
        while(cursor.moveToNext())
        {
            var id = cursor.getInt(0)
            var password = cursor.getString(1)
            var email = cursor.getString(2)
            var age = cursor.getInt(3)

            var us = User(id,password,email,age)

            arr.add(us)
        }
        return arr
        db.close()
    }
    fun insertFruit(fr:Fruit) :Long
    {
        var db = writableDatabase
        var cn = ContentValues()
        cn.put(NAME_FR,fr.F_Name)
        cn.put(PRICE_FR,fr.F_Price)
        cn.put(QTY_FR,fr.F_Qty)

        var res = db.insert(TB_FRUIT,null,cn)
        return res
        db.close()
    }
    fun GetAllFruits() : ArrayList<Fruit>
    {
        var db = readableDatabase
        var arr = ArrayList<Fruit>()
        var cursor = db.query(TB_FRUIT,null,null,null,null,null,null)
        while(cursor.moveToNext())
        {
            var id = cursor.getInt(0)
            var name = cursor.getString(1)
            var price = cursor.getDouble(2)
            var qty = cursor.getInt(3)

            var fr = Fruit(id,name,price,qty)

            arr.add(fr)
        }
        return arr
        db.close()
    }
    fun update(f:Fruit)
    {
        var db=writableDatabase
        var cv=ContentValues()
        cv.put(NAME_FR,f.F_Name)
        cv.put(PRICE_FR,f.F_Price)
        cv.put(QTY_FR,f.F_Qty)
        var flag=db.update(TB_FRUIT,cv,"$ID_FR=${f.F_Id}",null)
        db.close()
    }
    fun Delete(id:Int)
    {
        var db=writableDatabase
        db.delete(TB_FRUIT,"$ID_FR=$id",null)
        db.close()
    }
}