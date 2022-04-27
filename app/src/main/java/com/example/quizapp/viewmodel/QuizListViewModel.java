package com.example.quizapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.model.QuizModel;
import com.example.quizapp.repository.FirebaseRepository;

import java.util.List;

public class QuizListViewModel extends ViewModel implements FirebaseRepository.OnFirestoreTaskComplete {

    private FirebaseRepository firebaseRepository = new FirebaseRepository(this);

    public QuizListViewModel() {
        firebaseRepository.getQuizData();
    }

    private MutableLiveData<List<QuizModel>> quizListModelData = new MutableLiveData<>();

    public LiveData<List<QuizModel>> getQuizListModelData() {
        return quizListModelData;
    }

    @Override
    public void quizListDataAdded(List<QuizModel> quizModelList) {
        quizListModelData.setValue(quizModelList);
    }

    @Override
    public void onError(Exception e) {

    }
}
