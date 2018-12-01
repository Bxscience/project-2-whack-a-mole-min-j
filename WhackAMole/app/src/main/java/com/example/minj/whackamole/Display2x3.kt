package com.example.minj.whackamole

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.ViewGroup
import android.widget.*

const val SCORE = "com.example.minj.whackamole"

class Display2x3 : AppCompatActivity() {

    var num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display2x3)

        val score: TextView = findViewById(R.id.scoreView)


        //timer code
        val time: TextView = findViewById(R.id.timeView)

        var timeNumber = 60

        val timer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeNumber--
                time.text = "$timeNumber Seconds Remaining"
            }

            override fun onFinish() {
                reset()
                sendScore()
            }
        }
        timer.start()

        //grid code
        reset()
        var pos : Int = (0 .. 5).random()
        mThumbIds[pos] = R.drawable.diglet
        val gridview: GridView = findViewById(R.id.gridview)
        gridview.adapter = ImageAdapter(this)
        gridview.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            if (position == pos) {
                while (pos == position) {
                    pos = (0 .. 5).random()
                }
                mThumbIds[position] = R.drawable.transparent
                mThumbIds[pos] = R.drawable.diglet
                gridview.adapter = ImageAdapter(this)
                num++
                score.text = "$num"

            }
            else {
                num--
                score.text = "$num"
            }

        }
    }

    fun sendScore() {
        val score: String = num.toString()
        val intent = Intent(this, GameOver::class.java).apply {
            putExtra(SCORE, score)
        }
        startActivity(intent)
    }

    fun reset() {
        mThumbIds = arrayOf(
                R.drawable.transparent, R.drawable.transparent, R.drawable.transparent,
                R.drawable.transparent, R.drawable.transparent, R.drawable.transparent)
    }
}

private var mThumbIds = arrayOf (
        R.drawable.transparent, R.drawable.transparent, R.drawable.transparent,
        R.drawable.transparent, R.drawable.transparent, R.drawable.transparent
)

class ImageAdapter(private val mContext: Context) : BaseAdapter() {
    override fun getCount(): Int = mThumbIds.size
    override fun getItem(position: Int): Any? = null
    override fun getItemId(position: Int): Long = 0L

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val imageView: ImageView
        if (convertView == null) {
            imageView = ImageView(mContext)
            imageView.layoutParams = ViewGroup.LayoutParams(298, 384)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setPadding(0, 8, 0, 8)
        } else {
            imageView = convertView as ImageView
        }

        imageView.setImageResource(mThumbIds[position])
        return imageView
    }
}