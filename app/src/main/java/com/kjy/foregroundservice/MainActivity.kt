package com.kjy.foregroundservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.kjy.foregroundservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 시작 버튼 클릭 이벤트
        binding.buttonStart.setOnClickListener {
            // 서비스를 시작하는 코드
            val intent = Intent(this, Foreground::class.java)
            /*
            포어그라운드 서비스는 startService()가 아닌 ContextCompat.startForegroundService()를 사용해서
            실행해야 함.
             */
            ContextCompat.startForegroundService(this, intent)
        }

        // 종료 버튼 클릭 이벤트
        binding.buttonStop.setOnClickListener {
            val intent = Intent(this, Foreground::class.java)
            stopService(intent)

        }
    }
}