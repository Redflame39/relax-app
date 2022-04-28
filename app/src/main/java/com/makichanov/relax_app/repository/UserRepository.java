package com.makichanov.relax_app.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.makichanov.relax_app.model.persist.UserData;

import java.util.Collection;
import java.util.List;

@Dao
public interface UserRepository {

    @Query("select * from UserData")
    List<UserData> readAll();

    @Query("select * from UserData where id = :id")
    UserData read(long id);

    @Query("select * from UserData where username = :username")
    UserData read(String username);

    @Insert
    long save(UserData userData);

    @Insert
    void saveAll(Collection<UserData> users);

    @Update
    void update(UserData userData);

    @Delete
    void delete(UserData userData);

    @Query("delete from UserData")
    void deleteAll();

}
