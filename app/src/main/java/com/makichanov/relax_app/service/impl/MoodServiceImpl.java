package com.makichanov.relax_app.service.impl;

import com.makichanov.relax_app.RelaxAppContext;
import com.makichanov.relax_app.model.persist.MoodRecord;
import com.makichanov.relax_app.repository.MoodRepository;
import com.makichanov.relax_app.service.MoodService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class MoodServiceImpl implements MoodService {

    private RelaxAppContext relaxAppContext;
    private MoodRepository moodRepository;

    public MoodServiceImpl() {
        relaxAppContext = RelaxAppContext.getInstance();
        moodRepository = relaxAppContext.moodRepository();
    }

    @Override
    public Single<List<MoodRecord>> readMoodHistory(long userId) {
        return Single.fromCallable(() -> moodRepository.readAll(userId));
    }

    @Override
    public Single<MoodRecord> saveMood(long userId, String moodTitle) {
        MoodRecord moodRecord = new MoodRecord();
        moodRecord.setMoodTitle(moodTitle);
        moodRecord.setUserId(userId);
        String currentTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        moodRecord.setCommittedAt(currentTime);
        return Single.fromCallable(() -> {
            long id = moodRepository.save(moodRecord);
            return moodRepository.read(id);
        });


    }

    @Override
    public Single<List<MoodRecord>> readMoodHistory() {
        return Single.fromCallable(() -> moodRepository.readAll());
    }
}
