package com.example.a19bca058_external

class Fruit (var F_Name:String, var F_Price:Double, var F_Qty: Int) {
    var F_Id:Int = 0
    constructor(F_Id:Int, F_Name: String,F_Price: Double,F_Qty: Int): this(F_Name, F_Price, F_Qty)
    {
        this.F_Id =F_Id
    }
}