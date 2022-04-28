package com.example.quizapp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.quizapp.R;
import com.example.quizapp.model.QuizModel;
import com.example.quizapp.viewmodel.QuizListViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment implements View.OnClickListener {

    private NavController navController;
    private QuizListViewModel quizListViewModel;

    private ShapeableImageView detailImage;
    private MaterialTextView detailTitle, detailDesc, detailDiff, detailQuestion, detailScore;
    private MaterialButton detailBtn;
    int position;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        position = DetailFragmentArgs.fromBundle(getArguments()).getPosition();

        detailImage = view.findViewById(R.id.detail_image);
        detailTitle = view.findViewById(R.id.detail_title);
        detailDesc = view.findViewById(R.id.detail_desc);
        detailDiff = view.findViewById(R.id.detail_diff_text);
        detailQuestion = view.findViewById(R.id.detail_questions_text);
        detailBtn = view.findViewById(R.id.detail_start_btn);

        quizListViewModel = new ViewModelProvider(getActivity()).get(QuizListViewModel.class);
        quizListViewModel.getQuizListModelData().observe(getViewLifecycleOwner(), new Observer<List<QuizModel>>() {
            @Override
            public void onChanged(List<QuizModel> quizModels) {
                detailTitle.setText(quizModels.get(position).getName());

                String imageUrl = quizModels.get(position).getImage();
                Glide.with(view.getContext())
                        .load(imageUrl)
                        .centerCrop()
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(detailImage);

                detailDesc.setText(quizModels.get(position).getDesc());
                detailDiff.setText(quizModels.get(position).getDifficulty());
                detailQuestion.setText(String.valueOf(quizModels.get(position).getQuestions()));
            }
        });

        detailBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        DetailFragmentDirections.ActionDetailFragmentToQuizFragment action = DetailFragmentDirections.actionDetailFragmentToQuizFragment();
        action.setPosition(position);
        navController.navigate(R.id.action_detailFragment_to_quizFragment);
    }
}