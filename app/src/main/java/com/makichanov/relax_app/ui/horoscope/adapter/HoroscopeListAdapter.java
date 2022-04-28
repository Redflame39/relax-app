package com.makichanov.relax_app.ui.horoscope.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makichanov.relax_app.databinding.HoroscopeListItemBinding;
import com.makichanov.relax_app.model.entity.HoroscopeData;
import com.makichanov.relax_app.model.entity.HoroscopeSign;

import java.util.List;

public class HoroscopeListAdapter extends RecyclerView.Adapter<HoroscopeListAdapter.HoroscopeListItemViewHolder> {

    public static class HoroscopeListItemViewHolder extends RecyclerView.ViewHolder {

        private HoroscopeListItemBinding binding;

        public HoroscopeListItemViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = HoroscopeListItemBinding.bind(itemView);
        }

    }

    private List<HoroscopeData> horoscopeDataList;

    public HoroscopeListAdapter(List<HoroscopeData> horoscopeDataList) {
        this.horoscopeDataList = horoscopeDataList;
    }

    @Override
    public void onBindViewHolder(@NonNull HoroscopeListItemViewHolder holder, int position) {
        HoroscopeData horoscopeData = horoscopeDataList.get(position);
        String signName = horoscopeData.getName();
        HoroscopeSign sign = HoroscopeSign.valueOf(signName.toUpperCase());
        holder.binding.signImage.setImageResource(sign.getSignLogoResourceId());
        holder.binding.signName.setText(signName);
        holder.binding.signRangeText.setText(horoscopeData.getDateRange());
        holder.binding.compatibleWithText.setText(horoscopeData.getCompatibility());
        holder.binding.signColorText.setText(horoscopeData.getColor());
        holder.binding.description.setText(horoscopeData.getDescription());
    }

    @NonNull
    @Override
    public HoroscopeListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        HoroscopeListItemBinding binding = HoroscopeListItemBinding.inflate(inflater, parent, false);
        View view = binding.getRoot();

        return new HoroscopeListItemViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return horoscopeDataList.size();
    }

}
