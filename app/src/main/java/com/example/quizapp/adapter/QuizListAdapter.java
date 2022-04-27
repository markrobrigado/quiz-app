package com.example.quizapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.model.QuizModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.QuizViewHolder> {

    private List<QuizModel> quizModels;

    public void setQuizModels(List<QuizModel> quizModels) {
        this.quizModels = quizModels;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        holder.listTitle.setText(quizModels.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if(quizModels == null) {
            return 0;
        } else {
            return quizModels.size();
        }
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder {
        private ShapeableImageView listImage;
        private MaterialTextView listTitle, listDesc, listDiff;
        private MaterialButton listBtn;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);

            listImage = itemView.findViewById(R.id.list_item_image);
            listTitle = itemView.findViewById(R.id.list_item_title);
            listDesc = itemView.findViewById(R.id.list_item_description);
            listDiff = itemView.findViewById(R.id.list_item_diff);
            listBtn = itemView.findViewById(R.id.list_view_btn);
        }
    }
}
