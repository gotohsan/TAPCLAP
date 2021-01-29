package com.example.tapclap.ui.presenter;

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
import com.example.tapclap.databinding.FragmentPresenterBinding;

public class PresenterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_presenter, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PresenterViewModel presenterViewModel = new ViewModelProvider(this).get(PresenterViewModel.class);
        FragmentPresenterBinding presenterBinding = DataBindingUtil.bind(view);
        presenterBinding.setViewModel(presenterViewModel);
        presenterBinding.setLifecycleOwner(this);

        presenterViewModel.setCreateTitle(requireArguments().getString("createTitle"));

        Button sessionEndButton = view.findViewById(R.id.sessionEndButton);
        sessionEndButton.setOnClickListener(v -> presenterViewModel.getFireStoreDocument());

        Button sessionDisplayButton = view.findViewById(R.id.sessionDisplayButton);
        sessionDisplayButton.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_presenterFragment_to_resultFragment, presenterViewModel.getBundle()));
    }
}