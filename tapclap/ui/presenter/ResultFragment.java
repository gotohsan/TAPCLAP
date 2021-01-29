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
import com.example.tapclap.databinding.FragmentResultBinding;

import java.util.Objects;


public class ResultFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ResultViewModel resultViewModel = new ViewModelProvider(this).get(ResultViewModel.class);
        FragmentResultBinding resultBinding = DataBindingUtil.bind(view);
        Objects.requireNonNull(resultBinding).setViewModel(resultViewModel);
        resultBinding.setLifecycleOwner(this);

        resultViewModel.setExcellent(requireArguments().getString("excellent"));
        resultViewModel.setGood(requireArguments().getString("good"));
        resultViewModel.setSleepy(requireArguments().getString("sleepy"));
        resultViewModel.setBad(requireArguments().getString("bad"));

        Button endButton = view.findViewById(R.id.endButton);
        endButton.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_resultFragment_to_sessionFragment));
    }
}