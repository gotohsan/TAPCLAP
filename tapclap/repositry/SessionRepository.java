package com.example.tapclap.repositry;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SessionRepository {
    public List<String> titleList = new ArrayList<>();
    public List<Map<String, Object>> dataList = new ArrayList<>();
    public Map<String, Object> sessionMap = new HashMap<>();

    public void createSession(Map<String, Object> session, String sessionTitle) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("sessions")
                .document(sessionTitle)
                .set(session)
                .addOnSuccessListener(aVoid -> {
                })
                .addOnFailureListener(e -> {
                });
    }

    public void getSessionDocument(String title) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("sessions").document(title)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        sessionMap.putAll(Objects.requireNonNull(Objects.requireNonNull(document).getData()));
                    }
                });
    }

    public void setCountData(String session, int excellent, int good, int sleepy, int bad) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference washingtonRef = db.collection("sessions").document(session);
        washingtonRef.update("excellent", FieldValue.increment(excellent));
        washingtonRef.update("good", FieldValue.increment(good));
        washingtonRef.update("sleepy", FieldValue.increment(sleepy));
        washingtonRef.update("bad", FieldValue.increment(bad));
    }

    public void getSessionList() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        titleList.clear();
        dataList.clear();
        db.collection("sessions")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                            titleList.add(document.getId());
                            dataList.add(document.getData());
                        }
                    }
                });
    }

    public List<String> getTitle() {
        return titleList;
    }

    public List<Map<String, Object>> getData() {
        return dataList;
    }

    public Map<String, Object> getSessionDocumentMap() {
        return sessionMap;
    }
}
