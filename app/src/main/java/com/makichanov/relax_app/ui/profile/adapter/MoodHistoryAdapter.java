package com.makichanov.relax_app.ui.profile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makichanov.relax_app.databinding.MoodHistoryElementBinding;
import com.makichanov.relax_app.model.persist.MoodRecord;

import java.util.List;

public class MoodHistoryAdapter extends RecyclerView.Adapter<MoodHistoryAdapter.MoodHistoryViewHolder> {

    public class MoodHistoryViewHolder extends RecyclerView.ViewHolder {

        private MoodHistoryElementBinding binding;

        public MoodHistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = MoodHistoryElementBinding.bind(itemView);
        }
    }

    private List<MoodRecord> moodRecordList;

    public MoodHistoryAdapter(List<MoodRecord> moodRecordList) {
        this.moodRecordList = moodRecordList;
    }

    @NonNull
    @Override
    public MoodHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MoodHistoryElementBinding binding = MoodHistoryElementBinding.inflate(inflater, parent, false);
        View view = binding.getRoot();

        return new MoodHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoodHistoryViewHolder holder, int position) {
        MoodRecord moodRecord = moodRecordList.get(position);
        holder.binding.moodHistoryTitle.setText(moodRecord.getMoodTitle());
        holder.binding.moodHistoryDate.setText(moodRecord.getCommittedAt());
    }

    @Override
    public int getItemCount() {
        return moodRecordList.size();
    }

}
