package ru.pashaginas.loftcoin.activity;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ru.pashaginas.loftcoin.R;

public class WelcomeActivity extends AppCompatActivity {

   static final String KEY_SHOW_WELCOME = "show_welcome";

   @Override
   public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
      super.onCreate(savedInstanceState, persistentState);
      setContentView(R.layout.activity_welcome);
   }
}
