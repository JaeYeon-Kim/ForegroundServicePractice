package com.kjy.foregroundservice

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat


// 포어그라운드 서비스
// 오류를 막기 위해 비어 있는 Binder() 리턴
class Foreground : Service() {

    val CHANNEL_ID = "ForegroundChannel"
    override fun onBind(intent: Intent): IBinder {
        return Binder()

    }

    // 서비스에 사용할 알림을 실행하기 전에 알림 채널을 생성하는 메서드를 먼저 만들어 놓음.
    // 안드로이드 오레오 버전부터 모든 알림은 채널 단위로 동작하도록 설계되어 있음.
    fun createNotificationChannel() {
        if  (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel (
                CHANNEL_ID,
            "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT)
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // 알림 채널 생성
        createNotificationChannel()

        // 알림 생성, 제목과 아이콘 설정.
        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Foreground Service")
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .build()

        // 생성한 알림 실행
        startForeground(1, notification)
        return super.onStartCommand(intent, flags, startId)
    }

}