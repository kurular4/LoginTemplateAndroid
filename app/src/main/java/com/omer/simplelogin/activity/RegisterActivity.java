package com.omer.simplelogin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.omer.simplelogin.R;
import com.omer.simplelogin.constant.RegisterMessage;
import com.omer.simplelogin.databinding.ActivityRegisterBinding;
import com.omer.simplelogin.repository.UserRepository;
import com.omer.simplelogin.utils.Activities;
import com.omer.simplelogin.utils.GeneralUtils;
import com.omer.simplelogin.viewmodel.RegisterViewModel;
import com.omer.simplelogin.viewmodel.factory.ViewModelFactory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class RegisterActivity extends AppCompatActivity {
    private RegisterViewModel registerViewModel;

    @BindingAdapter({"toastMessage"})
    public static void showToastMessage(View view, String message) {
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegisterBinding activityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        registerViewModel = ViewModelProviders.of(this, new ViewModelFactory(getApplication())).get(RegisterViewModel.class);
        activityRegisterBinding.setViewModel(registerViewModel);
        activityRegisterBinding.executePendingBindings();

        UserRepository.getInstance().isRegisterSuccessful().observe(this, isSuccessful -> {
            if (isSuccessful) {
                Activities.navigateToActivity(this, LoginActivity.class);
            } else {
                GeneralUtils.createToastMessageShort(this, RegisterMessage.FAIL);
            }
        });
    }
}