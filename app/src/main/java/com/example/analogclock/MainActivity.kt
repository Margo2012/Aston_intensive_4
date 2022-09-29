package com.example.analogclock

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.analogclock.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val currentTime = Calendar.getInstance().time.toString()
        mBinding.customViewLabel.text = currentTime
    }
}