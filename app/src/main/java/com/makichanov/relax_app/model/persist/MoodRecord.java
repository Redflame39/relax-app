package com.makichanov.relax_app.model.persist;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MoodRecord {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "user_id")
    private long userId;

    @ColumnInfo(name = "mood_title")
    private String moodTitle;

    @ColumnInfo(name = "committed_at")
    private String committedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getMoodTitle() {
        return moodTitle;
    }

    public void setMoodTitle(String moodTitle) {
        this.moodTitle = moodTitle;
    }

    public String getCommittedAt() {
        return committedAt;
    }

    public void setCommittedAt(String committedAt) {
        this.committedAt = committedAt;
    }

}
