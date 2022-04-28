package com.makichanov.relax_app.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.makichanov.relax_app.model.persist.MoodRecord;
import com.makichanov.relax_app.model.persist.UserData;
import com.makichanov.relax_app.repository.MoodRepository;
import com.makichanov.relax_app.repository.UserRepository;

@Database(entities = {UserData.class, MoodRecord.class}, version = 3)
public abstract class RelaxAppDatabase extends RoomDatabase {

    public abstract UserRepository userRepository();

    public abstract MoodRepository moodRepository();

}
