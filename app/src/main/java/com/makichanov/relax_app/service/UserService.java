package com.makichanov.relax_app.service;

import com.makichanov.relax_app.model.persist.UserData;

import io.reactivex.rxjava3.core.Single;

public interface UserService {

    Single<UserData> authenticate(String login, String password);

    Single<UserData> createUser(String login, String email, String password);

}