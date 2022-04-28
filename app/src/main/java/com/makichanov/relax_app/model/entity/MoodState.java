package com.makichanov.relax_app.model.entity;

import com.makichanov.relax_app.R;

public enum MoodState {

    MOOD_CALM("Calm", R.drawable.calm),
    MOOD_FOCUS("Focus", R.drawable.focus),
    MOOD_RELAX("Relax", R.drawable.relax),
    EXCITED("Excited", R.drawable.excited_icon),
    DISAPPOINTED("Disappointed", R.drawable.fake_icon);

    MoodState(String moodTitle, Integer moodImageResourceId) {
        this.moodTitle = moodTitle;
        this.moodImageResourceId = moodImageResourceId;
    }

    private String moodTitle;

    private Integer moodImageResourceId;

    public String getMoodTitle() {
        return moodTitle;
    }

    public void setMoodTitle(String moodTitle) {
        this.moodTitle = moodTitle;
    }

    public Integer getMoodImage() {
        return moodImageResourceId;
    }

    public void setMoodImage(Integer moodImage) {
        this.moodImageResourceId = moodImage;
    }
}
