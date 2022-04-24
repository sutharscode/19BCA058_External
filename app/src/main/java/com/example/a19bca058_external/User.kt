package com.example.a19bca058_external

data class User (var password: String, var email:String, var age:Int) {
    var Id:Int = 0;
    constructor(Id:Int,password: String,email: String,age: Int):this(password,email,age)
    {
        this.Id = Id
    }
}