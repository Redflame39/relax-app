package com.makichanov.relax_app.service.impl;


import com.makichanov.relax_app.RelaxAppContext;
import com.makichanov.relax_app.model.persist.UserData;
import com.makichanov.relax_app.repository.UserRepository;
import com.makichanov.relax_app.service.UserService;

import io.reactivex.rxjava3.core.Single;

public class UserServiceImpl implements UserService {

    private RelaxAppContext relaxAppContext;
    private UserRepository userRepository;

    public UserServiceImpl() {
        relaxAppContext = RelaxAppContext.getInstance();
        userRepository = relaxAppContext.userRepository();
    }

    @Override
    public Single<UserData> authenticate(String login, String password) {
        return Single.fromCallable(() -> {
            UserData userData = userRepository.read(login);
            return userData.getPassword().equals(password)
                    ? userData
                    : null;
        });
    }

    @Override
    public Single<UserData> createUser(String login, String email, String password) {
        UserData userData = new UserData();
        userData.setUsername(login);
        userData.setEmail(email);
        userData.setPassword(password);
        return Single.fromCallable(() -> {
            long id = userRepository.save(userData);
            return userRepository.read(id);
        });
    }
}
