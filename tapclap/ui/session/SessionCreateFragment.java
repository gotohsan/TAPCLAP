package com.example.tapclap.ui.session;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.tapclap.R;
import com.example.tapclap.databinding.FragmentSessionCreateBinding;

import java.util.Objects;

public class SessionCreateFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_session_create, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SessionCreateViewModel sessionCreateViewModel = new ViewModelProvider(this).get(SessionCreateViewModel.class);
        FragmentSessionCreateBinding sessionCreateBinding = DataBindingUtil.bind(view);
        Objects.requireNonNull(sessionCreateBinding).setViewModel(sessionCreateViewModel);
        sessionCreateBinding.setLifecycleOwner(this);

        Button createButton = view.findViewById(R.id.createButton);
        createButton.setOnClickListener(v -> {
            sessionCreateViewModel.createSession();
            Navigation.findNavController(view).navigate(R.id.action_sessionCreateFragment_to_presenterFragment, sessionCreateViewModel.getBundle());
        });
    }
}