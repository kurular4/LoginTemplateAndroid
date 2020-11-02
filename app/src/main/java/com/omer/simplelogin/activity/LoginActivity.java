package com.omer.simplelogin.activity;

import android.os.Build;
import android.os.Bundle;

import com.omer.simplelogin.R;
import com.omer.simplelogin.constant.LoginMessage;
import com.omer.simplelogin.databinding.ActivityLoginBinding;
import com.omer.simplelogin.repository.PublicRepository;
import com.omer.simplelogin.utils.Activities;
import com.omer.simplelogin.utils.GeneralUtils;
import com.omer.simplelogin.viewmodel.LoginViewModel;
import com.omer.simplelogin.viewmodel.factory.ViewModelFactory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new ViewModelFactory(getApplication())).get(LoginViewModel.class);
        activityLoginBinding.setViewModel(loginViewModel);
        activityLoginBinding.executePendingBindings();

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.greenish_blue));
        }

        PublicRepository.getInstance().getUser().observe(this, user -> {
            if (user != null) {
                Activities.navigateToActivity(getApplicationContext(), MainActivity.class);
            } else {
                GeneralUtils.createToastMessageShort(this, LoginMessage.FAIL);
            }
        });
    }
}