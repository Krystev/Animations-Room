package com.inveitix.demo.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NotesDao
{
    @Insert
    void insert(Note note);

    @Query("DELETE FROM notes")
    void deleteAll();

    @Query("SELECT * from notes ORDER BY message ASC")
    LiveData<List<Note>> getAllWords();
}
