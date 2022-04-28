package com.makichanov.relax_app.service;

import com.makichanov.relax_app.model.persist.MoodRecord;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface MoodService {

    Single<MoodRecord> saveMood(long userId, String moodTitle);

    Single<List<MoodRecord>> readMoodHistory();

    Single<List<MoodRecord>> readMoodHistory(long userId);

}
