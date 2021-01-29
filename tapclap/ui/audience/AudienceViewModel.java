package com.example.taplap.ui.audience;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tapclap.repositry.SessionRepository;

public class AudienceViewModel extends ViewModel {
    SessionRepository sessionRepository = new SessionRepository();
    String sessionTitle;

    private final MutableLiveData<Integer> _excellentCount = new MutableLiveData<>();
    private final MutableLiveData<String> _excellentValue = new MutableLiveData<>();
    public LiveData<String> excellentValue = _excellentValue;

    private final MutableLiveData<Integer> _goodCount = new MutableLiveData<>();
    private final MutableLiveData<String> _goodValue = new MutableLiveData<>();
    public LiveData<String> goodValue = _goodValue;

    private final MutableLiveData<Integer> _sleepyCount = new MutableLiveData<>();
    private final MutableLiveData<String> _sleepyValue = new MutableLiveData<>();
    public LiveData<String> sleepyValue = _sleepyValue;

    private final MutableLiveData<Integer> _badCount = new MutableLiveData<>();
    private final MutableLiveData<String> _badValue = new MutableLiveData<>();
    public LiveData<String> badValue = _badValue;

    public AudienceViewModel() {
        _goodCount.setValue(0);
        _badCount.setValue(0);
        _sleepyCount.setValue(0);
        _excellentCount.setValue(0);

        _goodValue.setValue(String.valueOf(_goodCount.getValue()));
        _badValue.setValue(String.valueOf(_badCount.getValue()));
        _sleepyValue.setValue(String.valueOf(_sleepyCount.getValue()));
        _excellentValue.setValue(String.valueOf(_excellentCount.getValue()));
    }

    public void setSessionTitle(String sessionTitle) {
        this.sessionTitle = sessionTitle;
    }

    public void onClickExcellent(View view) {
        _excellentCount.setValue(_excellentCount.getValue() + 1);
        _excellentValue.setValue(String.valueOf(_excellentCount.getValue()));
    }

    public void onClickGood(View view) {
        _goodCount.setValue(_goodCount.getValue() + 1);
        _goodValue.setValue(String.valueOf(_goodCount.getValue()));
    }

    public void onClickSleepy(View view) {
        _sleepyCount.setValue(_sleepyCount.getValue() + 1);
        _sleepyValue.setValue(String.valueOf(_sleepyCount.getValue()));
    }

    public void onClickBad(View view) {
        _badCount.setValue(_badCount.getValue() + 1);
        _badValue.setValue(String.valueOf(_badCount.getValue()));
    }

    public void setUserData() {
        int excellent = _excellentCount.getValue();
        int good = _goodCount.getValue();
        int sleepy = _sleepyCount.getValue();
        int bad = _badCount.getValue();
        sessionRepository.setCountData(sessionTitle, excellent, good, sleepy, bad);
    }
}