package com.example.reviewer_app.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reviewer_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment {

    private CircularProgressIndicator startProgress;
    private MaterialTextView startFeedbackText;

    private FirebaseAuth firebaseAuth;
    private NavController navController;

    private static final String START_TAG = "START_LOG";

    public StartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        navController = Navigation.findNavController(view);

        startProgress = view.findViewById(R.id.start_progress);
        startFeedbackText = view.findViewById(R.id.start_feedback);

        startFeedbackText.setText("Checking account credentials...");
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        // If no account
        if(currentUser == null) {

            startFeedbackText.setText("Creating account...");

            // Create account anonymously
            firebaseAuth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        // Navigate to Homepage
                        startFeedbackText.setText("Account Created...");
                        navController.navigate(R.id.action_startFragment_to_listFragment);
                    } else {
                        Log.d(START_TAG, "Start Log: " + task.getException());
                    }
                }
            });
        } else {
            // Navigate to Homepage
            startFeedbackText.setText("Logged In");
            navController.navigate(R.id.action_startFragment_to_listFragment);
        }
    }
}