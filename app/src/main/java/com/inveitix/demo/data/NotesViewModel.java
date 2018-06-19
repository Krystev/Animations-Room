package com.inveitix.demo.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class NotesViewModel extends AndroidViewModel
{
    private NotesRepository mRepository;

    private LiveData<List<Note>> mData;

    public NotesViewModel (@NonNull Application application) {
        super(application);
        mRepository = new NotesRepository(application);
        mData = mRepository.getAllNodes();
    }

    public LiveData<List<Note>> getAllNodes() {
        return mData;
    }

    public void insert(Note note) {
        mRepository.insert(note);
    }

    public void deleteAll()
    {
        mRepository.deleteAll();
    }
}
