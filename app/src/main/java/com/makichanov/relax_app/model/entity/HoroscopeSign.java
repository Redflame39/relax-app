package com.makichanov.relax_app.model.entity;

import com.makichanov.relax_app.R;

public enum HoroscopeSign {

    ARIES(R.drawable.aries),
    TAURUS(R.drawable.taurus),
    GEMINI(R.drawable.gemini),
    CANCER(R.drawable.cancer),
    LEO(R.drawable.leo),
    VIRGO(R.drawable.virgo),
    LIBRA(R.drawable.libra),
    SCORPIO(R.drawable.scorpio),
    SAGITTARIUS(R.drawable.sagittarius),
    CAPRICORN(R.drawable.capricorn),
    AQUARIUS(R.drawable.aquarius),
    PISCES(R.drawable.pisces);

    HoroscopeSign(int signLogoResourceId) {
        this.signLogoResourceId = signLogoResourceId;
    }

    private int signLogoResourceId;

    public int getSignLogoResourceId() {
        return signLogoResourceId;
    }

}
