package com.omer.simplelogin.viewmodel;

import android.app.Application;

import com.omer.simplelogin.repository.PublicRepository;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.AndroidViewModel;

public class MainViewModel extends AndroidViewModel implements Observable {
    private static MainViewModel instance;

    private final PropertyChangeRegistry callbacks = new PropertyChangeRegistry();
    private final PublicRepository publicRepository = PublicRepository.getInstance();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public static MainViewModel getInstance(Application application) {
        if (instance == null) {
            instance = new MainViewModel(application);
        }
        return instance;
    }

    @Bindable
    public String getUsername() {
        return publicRepository.getUser().getValue().getUsername();
    }

    @Bindable
    public String getEmail() {
        return publicRepository.getUser().getValue().getEmail();
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callbacks.remove(callback);
    }

    void notifyPropertyChanged(int fieldId) {
        callbacks.notifyCallbacks(this, fieldId, null);
    }
}
