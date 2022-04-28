package com.makichanov.relax_app;

import android.content.Context;

import androidx.room.Room;

import com.makichanov.relax_app.db.RelaxAppDatabase;
import com.makichanov.relax_app.model.persist.UserData;
import com.makichanov.relax_app.provider.HoroscopeDataProvider;
import com.makichanov.relax_app.repository.MoodRepository;
import com.makichanov.relax_app.repository.UserRepository;
import com.makichanov.relax_app.service.MoodService;
import com.makichanov.relax_app.service.UserService;
import com.makichanov.relax_app.service.impl.MoodServiceImpl;
import com.makichanov.relax_app.service.impl.UserServiceImpl;

public class RelaxAppContext {

    private static Context applicationContext;
    private static final RelaxAppContext instance = new RelaxAppContext();
    private RelaxAppDatabase database;
    private UserData currentUser;

    private RelaxAppContext() {

    }

    public static RelaxAppContext getInstance() {
        return instance;
    }

    public static void setApplicationContext(Context context) {
        applicationContext = context;
    }

    public UserRepository userRepository() {
        if (database == null) {
            instantiateDatabase();
        }
        return database.userRepository();
    }

    public MoodRepository moodRepository() {
        if (database == null) {
            instantiateDatabase();
        }
        return database.moodRepository();
    }

    public UserService userService() {
        return new UserServiceImpl();
    }

    public MoodService moodService() {
        return new MoodServiceImpl();
    }

    public HoroscopeDataProvider horoscopeDataProvider() {
        return new HoroscopeDataProvider();
    }

    public UserData getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserData currentUser) {
        this.currentUser = currentUser;
    }

    private void instantiateDatabase() {
        database = Room.databaseBuilder(applicationContext, RelaxAppDatabase.class,
                "relax_app_db")
                .fallbackToDestructiveMigration()
                .build();
    }

}
