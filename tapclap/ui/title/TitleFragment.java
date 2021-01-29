package com.example.tapclap.ui.title;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.tapclap.R;
import com.example.tapclap.databinding.FragmentTitleBinding;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Collections;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;
import static com.example.tapclap.util.Constants.RC_SIGN_IN;

public class TitleFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_title, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TitleViewModel titleViewModel = new ViewModelProvider(this).get(TitleViewModel.class);
        FragmentTitleBinding mainBinding = DataBindingUtil.bind(view);
        Objects.requireNonNull(mainBinding).setViewModel(titleViewModel);
        mainBinding.setLifecycleOwner(this);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            Navigation.findNavController(view).navigate(R.id.action_titleFragment_to_sessionFragment);
        }

        SignInButton signInButton = view.findViewById(R.id.google_sign_in_button);
        signInButton.setOnClickListener(v -> createSignInIntent());
    }

    public void createSignInIntent() {
        AuthUI.IdpConfig googleIdp = new AuthUI.IdpConfig.GoogleBuilder()
                .build();

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Collections.singletonList(googleIdp))
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            if (resultCode == RESULT_OK) {
                Navigation.findNavController(requireView()).navigate(R.id.action_titleFragment_to_sessionFragment);
            } else {
                Toast.makeText(getContext(), "ログインが中止されました。", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
