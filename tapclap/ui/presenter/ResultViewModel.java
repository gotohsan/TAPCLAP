package com.example.tapclap.ui.presenter;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ResultViewModel extends ViewModel {

    private final MutableLiveData<String> _excellentResult = new MutableLiveData<>();
    public LiveData<String> excellentResult = _excellentResult;

    private final MutableLiveData<String> _goodResult = new MutableLiveData<>();
    public LiveData<String> goodResult = _goodResult;

    private final MutableLiveData<String> _sleepyResult = new MutableLiveData<>();
    public LiveData<String> sleepyResult = _sleepyResult;

    private final MutableLiveData<String> _badResult = new MutableLiveData<>();
    public LiveData<String> badResult = _badResult;

    public void setExcellent(String getExcellent) {
        _excellentResult.postValue(getExcellent);
    }

    public void setGood(String getGood) {
        _goodResult.postValue(getGood);
    }

    public void setSleepy(String getSleepy) {
        _sleepyResult.postValue(getSleepy);
    }

    public void setBad(String getBad) {
        _badResult.postValue(getBad);
    }


}
