package com.makichanov.relax_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.makichanov.relax_app.RelaxAppContext;
import com.makichanov.relax_app.databinding.ActivityRegisterBinding;
import com.makichanov.relax_app.model.persist.UserData;
import com.makichanov.relax_app.service.UserService;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private RelaxAppContext relaxAppContext;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        relaxAppContext = RelaxAppContext.getInstance();
        userService = relaxAppContext.userService();
    }

    public void onClickCreateAccount(View view) {
        String login = binding.loginInput.getText().toString();
        String email = binding.emailInput.getText().toString();
        String password = binding.passwordInput.getText().toString();
        String passwordConfirmation = binding.passwordConfirmationInput.getText().toString();
        if (password.equals(passwordConfirmation)) {
            Intent intent = new Intent(this, LoginActivity.class);
            userService.createUser(login, email, password)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new SingleObserver<UserData>() {

                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    binding.progressBar.setVisibility(ProgressBar.VISIBLE);
                }

                @Override
                public void onSuccess(@NonNull UserData userData) {
                    binding.progressBar.setVisibility(ProgressBar.INVISIBLE);
                    startActivity(intent);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    binding.progressBar.setVisibility(ProgressBar.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Error while saving user",
                            Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            });;
        } else {
            Toast.makeText(getApplicationContext(), "Password doesn't matches confirmation",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickSignIn(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}