package com.makichanov.relax_app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.makichanov.relax_app.R;
import com.makichanov.relax_app.RelaxAppContext;
import com.makichanov.relax_app.databinding.ActivityOnboardingBinding;
import com.makichanov.relax_app.db.RelaxAppDatabase;

public class OnboardingActivity extends AppCompatActivity {

    ActivityOnboardingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        RelaxAppContext.setApplicationContext(getApplicationContext());
        super.onCreate(savedInstanceState);

        binding = ActivityOnboardingBinding.inflate(getLayoutInflater());

        setContentView(R.layout.activity_onboarding);
    }

    public void onClickStart(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void onClickRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}