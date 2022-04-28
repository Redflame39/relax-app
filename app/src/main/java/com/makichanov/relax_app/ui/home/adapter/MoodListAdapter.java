package com.makichanov.relax_app.ui.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makichanov.relax_app.databinding.MoodListElementBinding;
import com.makichanov.relax_app.model.entity.MoodState;

import java.util.List;

public class MoodListAdapter extends RecyclerView.Adapter<MoodListAdapter.MoodListItemViewHolder> {

    public static class MoodListItemViewHolder extends RecyclerView.ViewHolder {

        private MoodListElementBinding binding;

        public MoodListItemViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = MoodListElementBinding.bind(itemView);
        }
    }

    private List<MoodState> moodStates;

    public MoodListAdapter(List<MoodState> moodStates) {
        this.moodStates = moodStates;
    }
    
    @NonNull
    @Override
    public MoodListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MoodListElementBinding binding = MoodListElementBinding.inflate(inflater, parent, false);
        View view = binding.getRoot();

        return new MoodListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoodListItemViewHolder holder, int position) {
        MoodState moodState = moodStates.get(position);
        holder.binding.moodTitle.setText(moodState.getMoodTitle());
        Glide.with(holder.binding.moodImage.getContext())
                .load(moodState.getMoodImage())
                .into(holder.binding.moodImage);
    }

    @Override
    public int getItemCount() {
        return moodStates.size();
    }

}
