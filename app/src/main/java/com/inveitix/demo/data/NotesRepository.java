package com.inveitix.demo.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class NotesRepository
{
    private NotesDao mNotesDao;
    private LiveData<List<Note>> mData;

    NotesRepository(Application application)
    {
        AppDatabase db = AppDatabase.getDatabase(application);
        mNotesDao = db.mNotesDao();
        mData = mNotesDao.getAllWords();
    }

    LiveData<List<Note>> getAllNodes()
    {
        return mData;
    }

    public void insert(Note note)
    {
        new InsertAsyncTask(mNotesDao).execute(note);
    }

    public void deleteAll()
    {
        new DeleteAsyncTask(mNotesDao).execute();
    }

    private static class InsertAsyncTask extends AsyncTask<Note, Void, Void>
    {

        private NotesDao mAsyncTaskDao;

        InsertAsyncTask(NotesDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params)
        {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private NotesDao mAsyncTaskDao;

        DeleteAsyncTask(NotesDao notesDao)
        {
            this.mAsyncTaskDao = notesDao;
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
}
