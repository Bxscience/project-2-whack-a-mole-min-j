package com.example.minj.whackamole

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.*

const val SCORE2 = "com.example.minj.whackamole"

class Display4x5 : AppCompatActivity() {

    var num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display4x5)

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
        var pos : Int = (0 .. 19).random()
        var pos2: Int = (0 .. 19).random()
        var pos3: Int = (0 .. 19).random()
        mThumbIds[pos] = R.drawable.diglet
        mThumbIds[pos2] = R.drawable.diglet
        mThumbIds[pos3] = R.drawable.diglet
        val gridview: GridView = findViewById(R.id.gridview)
        gridview.adapter = ImageAdapter2(this)
        gridview.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            if (position == pos) {
                while (pos == position) {
                    pos = (0 .. 11).random()
                }
                mThumbIds[position] = R.drawable.transparent
                mThumbIds[pos] = R.drawable.diglet
                mThumbIds[pos2] = R.drawable.diglet
                mThumbIds[pos3] = R.drawable.diglet
                gridview.adapter = ImageAdapter2(this)
                num++
                score.text = "$num"
            }
            else if (position == pos2) {
                while (pos2 == position) {
                    pos2 = (0 .. 11).random()
                }
                mThumbIds[position] = R.drawable.transparent
                mThumbIds[pos] = R.drawable.diglet
                mThumbIds[pos2] = R.drawable.diglet
                mThumbIds[pos3] = R.drawable.diglet
                gridview.adapter = ImageAdapter2(this)
                num++
                score.text = "$num"
            }
            else if (position == pos3) {
                while (pos3 == position) {
                    pos3 = (0 .. 11).random()
                }
                mThumbIds[position] = R.drawable.transparent
                mThumbIds[pos] = R.drawable.diglet
                mThumbIds[pos2] = R.drawable.diglet
                mThumbIds[pos3] = R.drawable.diglet
                gridview.adapter = ImageAdapter2(this)
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
            putExtra(SCORE2, score)
        }
        startActivity(intent)
    }

    fun reset() {
        mThumbIds = arrayOf(
                R.drawable.transparent, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent,
                R.drawable.transparent, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent,
                R.drawable.transparent, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent,
                R.drawable.transparent, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent,
                R.drawable.transparent, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent
        )
    }
}

private var mThumbIds = arrayOf (
        R.drawable.transparent, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent,
        R.drawable.transparent, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent,
        R.drawable.transparent, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent,
        R.drawable.transparent, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent,
        R.drawable.transparent, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent
)

class ImageAdapter2(private val mContext: Context) : BaseAdapter() {
    override fun getCount(): Int = mThumbIds.size
    override fun getItem(position: Int): Any? = null
    override fun getItemId(position: Int): Long = 0L

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val imageView: ImageView
        if (convertView == null) {
            imageView = ImageView(mContext)
            imageView.layoutParams = ViewGroup.LayoutParams(223, 288)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setPadding(0, 8, 0, 8)
        } else {
            imageView = convertView as ImageView
        }

        imageView.setImageResource(mThumbIds[position])
        return imageView
    }
}