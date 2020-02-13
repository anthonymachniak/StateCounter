package com.example.statecounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

private val STATE_COUNTER = "counter"

class MainActivity : AppCompatActivity() {
    private var mCounterTextView: TextView?= null
    private var mCounter: Int = 0
    private var increment: Button?= null
    private var savePreferences: Button?= null
    private var clearPreference: Button?= null
    private var buttonDoesNotSave: Button?= null
    private var buttonDoesSave: Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pref = applicationContext.getSharedPreferences("MyPref", 0)
        val editor = pref.edit()

        mCounter = pref.getInt("number", 0)

        mCounterTextView = findViewById(R.id.mCounterTextView)
        mCounterTextView?.text = mCounter.toString()
        increment = findViewById(R.id.buttonIncrement)
        savePreferences = findViewById(R.id.save)
        clearPreference = findViewById(R.id.clear)
        buttonDoesNotSave = findViewById(R.id.buttonDoesNotSave)
        buttonDoesSave = findViewById(R.id.buttonDoesSave)

        increment?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View) {
                mCounter += 1
                mCounterTextView?.text = mCounter.toString()

                buttonDoesNotSave?.text = mCounter.toString()
                buttonDoesSave?.text = mCounter.toString()
            }
        })

        savePreferences?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View) {
                editor.putInt("number", mCounter)
                editor.commit()
            }
        })

        clearPreference?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mCounter = 0
                mCounterTextView?.text = mCounter.toString()

                editor.remove("number")
                editor.commit()
            }
        })
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
            mCounter = savedInstanceState.getInt(STATE_COUNTER)
            buttonDoesSave?.text = mCounter.toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_COUNTER, mCounter)
        super.onSaveInstanceState(outState)
    }
}
