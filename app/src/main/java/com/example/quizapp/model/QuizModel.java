package com.example.quizapp.model;

import com.google.firebase.firestore.DocumentId;

public class QuizModel {

    @DocumentId
    private String quizId;
    private String name;
    private String desc;
    private String image;
    private String difficulty;
    private long questions;

    // Empty constructor for Firebase
    public QuizModel() { }

    public QuizModel(String quizId, String name, String desc, long questions) {
        this.quizId = quizId;
        this.name = name;
        this.desc = desc;
        this.questions = questions;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public long getQuestions() {
        return questions;
    }

    public void setQuestions(long questions) {
        this.questions = questions;
    }
}
