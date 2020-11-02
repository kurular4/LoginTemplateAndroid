package com.omer.simplelogin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.omer.simplelogin.R;
import com.omer.simplelogin.databinding.ActivityMainBinding;
import com.omer.simplelogin.viewmodel.MainViewModel;
import com.omer.simplelogin.viewmodel.factory.ViewModelFactory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;

    @BindingAdapter({"toastMessage"})
    public static void showToastMessage(View view, String message) {
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = ViewModelProviders.of(this, new ViewModelFactory(getApplication())).get(MainViewModel.class);
        activityMainBinding.setViewModel(mainViewModel);
        activityMainBinding.executePendingBindings();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}