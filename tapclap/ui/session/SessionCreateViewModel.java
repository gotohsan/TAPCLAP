package com.example.tapclap.ui.session;

import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tapclap.repositry.SessionRepository;
import com.example.tapclap.repositry.UserRepository;

import java.util.HashMap;
import java.util.Map;

public class SessionCreateViewModel extends ViewModel {
    SessionRepository sessionRepository = new SessionRepository();
    UserRepository userRepository = new UserRepository();
    Map<String, Object> sessionInfo = new HashMap<>();

    public MutableLiveData<String> sessionTitle = new MutableLiveData<>();
    public MutableLiveData<String> sessionExp = new MutableLiveData<>();

    public SessionCreateViewModel() {
        sessionTitle.setValue("");
        sessionExp.setValue("");
    }

    public void createSession() {
        String userName = userRepository.getUserName();
        String userUid = userRepository.getUserUid();
        String title = sessionTitle.getValue();
        String explanation = sessionExp.getValue();

        sessionInfo.put("creatorName", userName);
        sessionInfo.put("creatorUid", userUid);
        sessionInfo.put("title", title);
        sessionInfo.put("explanation", explanation);
        sessionInfo.put("excellent", 0);
        sessionInfo.put("good", 0);
        sessionInfo.put("sleepy", 0);
        sessionInfo.put("bad", 0);

        sessionRepository.createSession(sessionInfo, title);
    }

    public Bundle getBundle() {
        Bundle createBundle = new Bundle();
        createBundle.putString("createTitle", getSessionTitle());
        return createBundle;
    }

    private String getSessionTitle() {
        return sessionTitle.getValue();
    }
}
