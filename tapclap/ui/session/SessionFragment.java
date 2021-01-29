package com.example.tapclap.ui.session;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.tapclap.R;
import com.example.tapclap.databinding.FragmentSessionBinding;
import com.firebase.ui.auth.AuthUI;

import java.util.Objects;

public class SessionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_session, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SessionViewModel sessionViewModel = new ViewModelProvider(this).get(SessionViewModel.class);
        FragmentSessionBinding sessionBinding = DataBindingUtil.bind(view);
        Objects.requireNonNull(sessionBinding).setViewModel(sessionViewModel);
        sessionBinding.setLifecycleOwner(this);
        sessionViewModel.getFireStoreData();

        Button signOutButton = view.findViewById(R.id.sign_out_button);
        signOutButton.setOnClickListener(
                v -> AuthUI.getInstance()
                        .signOut(requireContext())
                        .addOnCompleteListener(task -> Navigation.findNavController(view).navigate(R.id.action_sessionFragment_to_titleFragment))
        );

        Button createButton = view.findViewById(R.id.createButton);
        createButton.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_sessionFragment_to_sessionCreateFragment));

        Button searchButton = view.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(
                v -> {
                    if (sessionViewModel.searchSession()) {
                        Navigation.findNavController(view).navigate(R.id.action_sessionFragment_to_sessionDetailFragment, sessionViewModel.getBundle());
                    } else {
                        Toast.makeText(getContext(), "セッションを見つけられませんでした。", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
