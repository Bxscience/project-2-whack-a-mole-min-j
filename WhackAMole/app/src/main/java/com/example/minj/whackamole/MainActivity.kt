package com.example.minj.whackamole

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openTwoByThree(view: View) {
        val intent = Intent(this, Display2x3::class.java)
        startActivity(intent)
    }

    fun openThreeByFour(view: View) {
        val intent = Intent(this, Display3x4::class.java)
        startActivity(intent)
    }

    fun openFourByFive(view: View) {
        val intent = Intent(this, Display4x5::class.java)
        startActivity(intent)
    }
}
