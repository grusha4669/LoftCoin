package ru.pashaginas.loftcoin;

import android.app.Application;
import android.os.StrictMode;

class LoftApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults();
        }
    }
}
