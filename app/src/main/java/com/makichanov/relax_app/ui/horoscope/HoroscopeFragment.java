package com.makichanov.relax_app.ui.horoscope;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makichanov.relax_app.RelaxAppContext;
import com.makichanov.relax_app.databinding.FragmentHoroscopeBinding;
import com.makichanov.relax_app.model.entity.HoroscopeData;
import com.makichanov.relax_app.model.entity.HoroscopeSign;
import com.makichanov.relax_app.provider.HoroscopeDataProvider;
import com.makichanov.relax_app.ui.home.adapter.MoodListAdapter;
import com.makichanov.relax_app.ui.horoscope.adapter.HoroscopeListAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Response;


public class HoroscopeFragment extends Fragment {

    private FragmentHoroscopeBinding binding;
    private RelaxAppContext relaxAppContext;
    private HoroscopeListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHoroscopeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        relaxAppContext = RelaxAppContext.getInstance();

        List<HoroscopeData> horoscopeDataList = new ArrayList<>();
        RecyclerView recyclerView = binding.horoscopeList;
        adapter = new HoroscopeListAdapter(horoscopeDataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        binding.horoscopeProgressBar.setVisibility(ProgressBar.VISIBLE);
        HoroscopeDataProvider provider = relaxAppContext.horoscopeDataProvider();
        for (HoroscopeSign sign : HoroscopeSign.values()) {
            provider.getTodayDataBySign(sign.name())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new SingleObserver<Response>() {
                        @Override
                        public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                        }

                        @Override
                        public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull Response response) {
                            try {
                                String horoscopeDataString = response.body().string();
                                ObjectMapper objectMapper = new ObjectMapper();
                                HoroscopeData horoscopeData =
                                        objectMapper.readValue(horoscopeDataString, HoroscopeData.class);
                                horoscopeData.setName(sign.name());
                                horoscopeDataList.add(horoscopeData);
                                adapter.notifyItemInserted(horoscopeDataList.size() - 1);
                            } catch (IOException e) {
                                Log.e("Error", "Failed to fetch horoscope data for sign "
                                        + sign, e);
                            }
                        }

                        @Override
                        public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                            Toast.makeText(getContext(), "Error fetching horoscope data",
                                    Toast.LENGTH_SHORT).show();
                            Log.e("Error", "Error fetching horoscope data", e);
                        }
                    });
        }

        binding.horoscopeProgressBar.setVisibility(ProgressBar.INVISIBLE);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}