package com.inveitix.demo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inveitix.demo.data.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder>
{


    private List<Note> notes;

    public NotesAdapter()
    {
        this.notes = new ArrayList<>();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View viewHolder = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);

        return new NoteViewHolder(viewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position)
    {
        holder.txtNote.setText(notes.get(position).getMessage());
    }

    @Override
    public int getItemCount()
    {
        return notes.size();
    }

    public void setData(List<Note> notes)
    {
        this.notes = notes;
        notifyDataSetChanged();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtNote;

        public NoteViewHolder(View itemView)
        {
            super(itemView);
            this.txtNote = itemView.findViewById(R.id.txt_node);
        }
    }
}
