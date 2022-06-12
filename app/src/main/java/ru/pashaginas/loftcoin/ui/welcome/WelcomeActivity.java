package ru.pashaginas.loftcoin.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import ru.pashaginas.loftcoin.databinding.ActivityWelcomeBinding;
import ru.pashaginas.loftcoin.ui.main.MainActivity;
import ru.pashaginas.loftcoin.widget.CircleIndicator;

public class WelcomeActivity extends AppCompatActivity {

    public static final String KEY_SHOW_WELCOME = "show_welcome";

    private SnapHelper helper;
    private ActivityWelcomeBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityWelcomeBinding binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        binding.recycler.addItemDecoration(new CircleIndicator(this));
        binding.recycler.setAdapter(new WelcomeAdapter());
        binding.recycler.setHasFixedSize(true);
        helper = new PagerSnapHelper();
        helper.attachToRecyclerView(binding.recycler);
        binding.btnStart.setOnClickListener((v) -> {
            PreferenceManager.getDefaultSharedPreferences(this).edit()
                    .putBoolean(KEY_SHOW_WELCOME, true) // false
                    .apply();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        helper.attachToRecyclerView(null);
//        binding.recycler.setAdapter(null); //crash
        super.onDestroy();
    }
}
