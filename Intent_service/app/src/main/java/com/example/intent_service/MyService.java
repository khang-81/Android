package com.example.intent_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Your service logic here
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Cleanup code here
    }
}