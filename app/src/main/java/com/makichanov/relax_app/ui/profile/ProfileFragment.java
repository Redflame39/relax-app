package com.makichanov.relax_app.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.makichanov.relax_app.RelaxAppContext;
import com.makichanov.relax_app.databinding.FragmentProfileBinding;
import com.makichanov.relax_app.model.persist.MoodRecord;
import com.makichanov.relax_app.model.persist.UserData;
import com.makichanov.relax_app.service.MoodService;
import com.makichanov.relax_app.ui.profile.adapter.MoodHistoryAdapter;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfileFragment extends Fragment {

    private RelaxAppContext relaxAppContext;
    private FragmentProfileBinding binding;
    private MoodHistoryAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        relaxAppContext = RelaxAppContext.getInstance();

        UserData currentUser = relaxAppContext.getCurrentUser();

        binding.profileUsername.setText(currentUser.getUsername());
        binding.profileEmail.setText(currentUser.getEmail());

        MoodService service = relaxAppContext.moodService();
        service.readMoodHistory(currentUser.getId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new SingleObserver<List<MoodRecord>>() {

                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<MoodRecord> moodRecordList) {
                        RecyclerView recyclerView = binding.profileMoodHistory;
                        adapter = new MoodHistoryAdapter(moodRecordList);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                                LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Toast.makeText(getContext(), "Failed to load mood history",
                                Toast.LENGTH_SHORT).show();
                    }

                });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}