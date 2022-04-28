package com.makichanov.relax_app.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.makichanov.relax_app.RelaxAppContext;
import com.makichanov.relax_app.databinding.FragmentHomeBinding;
import com.makichanov.relax_app.model.entity.MoodState;
import com.makichanov.relax_app.model.persist.MoodRecord;
import com.makichanov.relax_app.listener.RecyclerItemClickListener;
import com.makichanov.relax_app.service.MoodService;
import com.makichanov.relax_app.ui.home.adapter.MoodListAdapter;

import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private MoodListAdapter moodListAdapter;
    private RelaxAppContext relaxAppContext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        relaxAppContext = RelaxAppContext.getInstance();

        final TextView textView = binding.textWelcome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        List<MoodState> moodStates = Arrays.asList(MoodState.values());
        RecyclerView recyclerView = binding.moodList;
        moodListAdapter = new MoodListAdapter(moodStates);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(moodListAdapter);
        moodListAdapter.notifyDataSetChanged();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                List<MoodState> moodStates =
                                        Arrays.asList(MoodState.values());
                                MoodState selected = moodStates.get(position);
                                MoodService service = relaxAppContext.moodService();
                                service.saveMood(relaxAppContext.getCurrentUser().getId(),
                                        selected.getMoodTitle())
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(new SingleObserver<MoodRecord>() {
                                    @Override
                                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                                    }

                                    @Override
                                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull MoodRecord moodRecord) {
                                        Toast.makeText(getContext(), "Mood saved!",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                        Toast.makeText(getContext(), "Error saving mood!",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                            }
                        })
        );

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}