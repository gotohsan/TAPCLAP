package com.example.tapclap.ui.presenter;

import android.os.Bundle;

import androidx.lifecycle.ViewModel;

import com.example.tapclap.repositry.SessionRepository;

import java.util.HashMap;
import java.util.Map;

public class PresenterViewModel extends ViewModel {
    SessionRepository sessionRepository = new SessionRepository();
    String createTitle;
    Map<String, Object> resultMap = new HashMap<>();

    public void getFireStoreDocument() {
        sessionRepository.getSessionDocument(createTitle);
    }

    public void setCreateTitle(String createTitle) {
        this.createTitle = createTitle;
    }

    public Bundle getBundle() {
        Bundle resultBundle = new Bundle();
        resultMap = sessionRepository.getSessionDocumentMap();
        resultBundle.putString("excellent", String.valueOf(sessionRepository.getSessionDocumentMap().get("excellent")));
        resultBundle.putString("good", String.valueOf(sessionRepository.getSessionDocumentMap().get("good")));
        resultBundle.putString("sleepy", String.valueOf(sessionRepository.getSessionDocumentMap().get("sleepy")));
        resultBundle.putString("bad", String.valueOf(sessionRepository.getSessionDocumentMap().get("bad")));
        return resultBundle;
    }
}
