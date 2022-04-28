package com.example.quizapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quizapp.R;
import com.example.quizapp.model.QuizModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.QuizViewHolder> {

    private OnQuizListItemClicked onQuizListItemClicked;
    private List<QuizModel> quizModels;

    public QuizListAdapter(OnQuizListItemClicked onQuizListItemClicked) {
        this.onQuizListItemClicked = onQuizListItemClicked;
    }

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

        String imageUrl = quizModels.get(position).getImage();

        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.listImage);

        holder.listDesc.setText(quizModels.get(position).getDesc());
        holder.listDiff.setText(quizModels.get(position).getDifficulty());
    }

    @Override
    public int getItemCount() {
        if(quizModels == null) {
            return 0;
        } else {
            return quizModels.size();
        }
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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

            listBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onQuizListItemClicked.onItemClicked(getAdapterPosition());
        }
    }

    public interface OnQuizListItemClicked {
        void onItemClicked(int position);
    }
}
