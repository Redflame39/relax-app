package com.makichanov.relax_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.makichanov.relax_app.RelaxAppContext;
import com.makichanov.relax_app.databinding.ActivityLoginBinding;
import com.makichanov.relax_app.model.persist.UserData;
import com.makichanov.relax_app.service.UserService;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private RelaxAppContext relaxAppContext;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        relaxAppContext = RelaxAppContext.getInstance();
        userService = relaxAppContext.userService();
    }

    public void onClickLogin(View view) {
        String login = binding.loginInputSignin.getText().toString();
        String password = binding.passwordInputSignin.getText().toString();
        Intent intent = new Intent(this, MainActivity.class);
        userService.authenticate(login, password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new SingleObserver<UserData>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        binding.progressBar.setVisibility(ProgressBar.VISIBLE);
                    }

                    @Override
                    public void onSuccess(@NonNull UserData userData) {
                        if (userData.getPassword().equals(password)) {
                            binding.progressBar.setVisibility(ProgressBar.INVISIBLE);
                            relaxAppContext.setCurrentUser(userData);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "User not found",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        binding.progressBar.setVisibility(ProgressBar.INVISIBLE);
                        Toast.makeText(getApplicationContext(), "Failed to sign in",
                                Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                });
    }

    public void onClickCreateAccount(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}