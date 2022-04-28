package com.makichanov.relax_app.repository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.makichanov.relax_app.model.persist.MoodRecord;

import java.util.List;

@Dao
public interface MoodRepository {

    @Query("select * from MoodRecord")
    List<MoodRecord> readAll();

    @Query("select * from MoodRecord where user_id = :userId")
    List<MoodRecord> readAll(long userId);

    @Query("select * from MoodRecord where id = :id")
    MoodRecord read(long id);

    @Insert
    long save(MoodRecord moodRecord);

}
