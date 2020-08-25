package com.example.gamephone;

import android.app.Application;

public class GameApp extends Application implements GamePermissionAccessible{
    @Override
    public void onCreate() {
        super.onCreate();
        GameSharedPreferences.getGameSharedPreferences(this);

    }
}
