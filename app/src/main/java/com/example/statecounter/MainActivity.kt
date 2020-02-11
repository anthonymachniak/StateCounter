package com.example.statecounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

private val STATE_COUNTER = "counter"

class MainActivity : AppCompatActivity() {

    private var mCounterTextView: TextView?= null
    private var mCounter: Int = 0
    private var button: Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            mCounter = savedInstanceState.getInt(STATE_COUNTER, 0)
        }

        mCounterTextView = findViewById(R.id.mCounterTextView)
        mCounterTextView?.text = mCounter.toString()

        button?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View) {
                mCounter += 1

                var mCounterString = mCounter.toString()
                Log.DEBUG("Tag", "Counter: $mCounterString")
                mCounterTextView?.text = mCounter.toString()
            }

        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_COUNTER, mCounter)
    }
}
