package com.makichanov.relax_app.provider;

import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makichanov.relax_app.model.entity.HoroscopeData;

import java.io.IOException;

import io.reactivex.rxjava3.core.Single;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HoroscopeDataProvider {

    private final String HOROSCOPE_API_URL = "https://aztro.sameerkumar.website";
    private final String PARAM_SIGN = "sign";
    private final String PARAM_DAY = "day";
    private final String PARAM_DAY_VALUE_TODAY = "today";

    public Single<Response> getTodayDataBySign(String sign) {
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder
                = HttpUrl.parse(HOROSCOPE_API_URL).newBuilder();
        urlBuilder.addQueryParameter(PARAM_SIGN, sign);
        urlBuilder.addQueryParameter(PARAM_DAY, PARAM_DAY_VALUE_TODAY);
        RequestBody body = RequestBody.create(null, new byte[0]);
        Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .post(body)
                .header("Content-Length", "0")
                .build();
        Call call = client.newCall(request);

        return Single.fromCallable(call::execute);
    }

}
