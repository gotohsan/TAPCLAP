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
import com.example.tapclap.databinding.FragmentSessionDetailBinding;

import java.util.Objects;


public class SessionDetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_session_detail, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SessionDetailViewModel sessionDetailViewModel = new ViewModelProvider(this).get(SessionDetailViewModel.class);
        FragmentSessionDetailBinding sessionDetailBinding = DataBindingUtil.bind(view);
        Objects.requireNonNull(sessionDetailBinding).setViewModel(sessionDetailViewModel);
        sessionDetailBinding.setLifecycleOwner(this);

        sessionDetailViewModel.setCreatorName(requireArguments().getString("creatorName"));
        sessionDetailViewModel.setTitle(requireArguments().getString("title"));
        sessionDetailViewModel.setExplanation(requireArguments().getString("explanation"));

        Button audienceButton = view.findViewById(R.id.audienceButton);
        audienceButton.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_sessionDetailFragment_to_audienceFragment, sessionDetailViewModel.getBundle()));
    }
}