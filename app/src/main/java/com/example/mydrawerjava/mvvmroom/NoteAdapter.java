package com.example.mydrawerjava.mvvmroom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydrawerjava.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private List<Note> notes;

    private OnDeleteClickListener listener;

    public void setNotes(List<Note> notes, OnDeleteClickListener listener) {
        this.notes = notes;
        this.listener = listener;
        notifyDataSetChanged();
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(Note note);

        void onViewClick(Note note);
    }


    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDescription.setText(currentNote.getDescription());
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteClick(currentNote);
            }
        });

        holder.viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onViewClick(currentNote);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes != null ? notes.size() : 0;
    }

    static class NoteHolder extends RecyclerView.ViewHolder {
        private final TextView textViewTitle;
        private final TextView textViewDescription;

        private final Button deleteBtn;

        private final Button viewBtn;

        public NoteHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            deleteBtn = itemView.findViewById(R.id.delete_note);
            viewBtn = itemView.findViewById(R.id.view_note);
        }
    }
}
