package com.example.tapclap.ui.session;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SessionDetailViewModel extends ViewModel {

    private final MutableLiveData<String> _creatorName = new MutableLiveData<>();
    public LiveData<String> creatorName = _creatorName;

    private final MutableLiveData<String> _title = new MutableLiveData<>();
    public LiveData<String> title = _title;

    private final MutableLiveData<String> _explanation = new MutableLiveData<>();
    public LiveData<String> explanation = _explanation;

    public void setCreatorName(String getName) {
        _creatorName.postValue(getName);
    }

    public void setTitle(String getTitle) {
        _title.postValue(getTitle);
    }

    public void setExplanation(String getExplanation) {
        _explanation.postValue(getExplanation);
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("title", _title.getValue());
        return bundle;
    }
}
