package com.example.tapclap.ui.session;

import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tapclap.repositry.SessionRepository;

import java.util.Map;

public class SessionViewModel extends ViewModel {

    SessionRepository sessionRepository = new SessionRepository();

    public MutableLiveData<String> searchTitle = new MutableLiveData<>();

    public void getFireStoreData() {
        sessionRepository.getSessionList();
    }

    public boolean searchSession() {
        boolean result = false;
        for (String titleList : sessionRepository.getTitle()) {
            if (titleList.equals(getSearchTitle())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        for (Map<String, Object> map : sessionRepository.getData()) {
            if (String.valueOf(map.get("title")).equals(getSearchTitle())) {
                bundle.putString("explanation", (String) map.get("explanation"));
                bundle.putString("title", (String) map.get("title"));
                bundle.putString("creatorName", (String) map.get("creatorName"));
                break;
            }
        }
        return bundle;
    }

    private String getSearchTitle() {
        return searchTitle.getValue();
    }
}
