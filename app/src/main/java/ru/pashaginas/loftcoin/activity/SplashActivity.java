package ru.pashaginas.loftcoin.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;

import java.util.prefs.PreferenceChangeEvent;

import ru.pashaginas.loftcoin.R;

public class SplashActivity extends AppCompatActivity {

    private final Handler handler = new Handler();

    private Runnable goNext;

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getBoolean(WelcomeActivity.KEY_SHOW_WELCOME, true)) {
            goNext = () -> startActivity(new Intent(this, WelcomeActivity.class));
        } else {
           goNext = () -> startActivity(new Intent(this, MainActivity.class));
        }
        handler.postDelayed(goNext, 1500);
    }

   @Override
   protected void onStop() {
       handler.removeCallbacks(goNext);
      super.onStop();
   }
}
