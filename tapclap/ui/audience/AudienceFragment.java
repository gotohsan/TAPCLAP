package com.example.tapclap.ui.audience;

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
import com.example.tapclap.databinding.FragmentAudienceBinding;

import java.util.Objects;

public class AudienceFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_audience, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AudienceViewModel audienceViewModel = new ViewModelProvider(this).get(AudienceViewModel.class);
        FragmentAudienceBinding audienceBinding = DataBindingUtil.bind(view);
        Objects.requireNonNull(audienceBinding).setViewModel(audienceViewModel);
        audienceBinding.setLifecycleOwner(this);

        audienceViewModel.setSessionTitle(requireArguments().getString("title"));

        Button endButton = view.findViewById(R.id.endButton);
        endButton.setOnClickListener(v -> {
            audienceViewModel.setUserData();
            Navigation.findNavController(view).navigate(R.id.action_audienceFragment_to_sessionFragment);
        });
    }
}