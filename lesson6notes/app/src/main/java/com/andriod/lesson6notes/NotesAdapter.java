package com.andriod.lesson6notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;
import androidx.recyclerview.widget.SortedListAdapterCallback;

import com.andriod.lesson6notes.data.Note;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private static SortedList<Note> notes;
    private final Fragment fragment;

    private int currentItem;

    public NotesAdapter(Fragment fragment) {
        this.fragment = fragment;

        if (notes == null) {
            notes = new SortedList<>(Note.class, new SortedListCallback(this));
            notes.addAll(MainActivity.getNotesManager().getNotes());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_note, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, DetailsFragment.OnNotesChangedListener {

        private static final int MESSAGE_LENGTH = 30;
        private final TextView textHeader;
        private final TextView textDate;
        private final TextView textMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textHeader = itemView.findViewById(R.id.textHeader);
            textDate = itemView.findViewById(R.id.textDate);
            textMessage = itemView.findViewById(R.id.textMessage);

            itemView.setOnClickListener(this);
        }

        public void setData(Note note) {
            textHeader.setText(note.getHeader());
            textDate.setText(note.getDate());

            String message;
            if (note.getMessage().length() > MESSAGE_LENGTH)
                message = String.format("%s...", note.getMessage().substring(0, MESSAGE_LENGTH));
            else
                message = note.getMessage();
            textMessage.setText(message);
        }

        @Override
        public void onClick(View v) {
            currentItem = getAdapterPosition();

            if (MainActivity.isIsLandscape()) {
                fragment.getFragmentManager().beginTransaction()
                        .replace(R.id.detailsContainer, DetailsFragment.newInstance(currentItem).setListener(this))
                        .addToBackStack(null)
                        .commit();
            } else {
                fragment.getFragmentManager().beginTransaction()
                        .replace(R.id.mainContainer, DetailsFragment.newInstance(currentItem).setListener(this))
                        .addToBackStack(null)
                        .commit();
            }
        }

        @Override
        public void onChange(int listIndex) {
            Toast.makeText(fragment.getContext(), "onChanged:" + listIndex, Toast.LENGTH_SHORT).show();
            notes.updateItemAt(listIndex, notes.get(listIndex));
        }
    }

    static class SortedListCallback extends SortedListAdapterCallback<Note> {

        public SortedListCallback(RecyclerView.Adapter adapter) {
            super(adapter);
        }

        @Override
        public int compare(Note o1, Note o2) {
            return o1.compare(o2);
        }

        @Override
        public boolean areContentsTheSame(Note oldItem, Note newItem) {
            return false;
        }

        @Override
        public boolean areItemsTheSame(Note item1, Note item2) {
            return false;
        }
    }
}
