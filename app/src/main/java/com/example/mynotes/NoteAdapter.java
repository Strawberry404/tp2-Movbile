package com.example.mynotes;
import com.example.mynotes.models.Note;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class NoteAdapter extends ArrayAdapter<Note> {
    public NoteAdapter(Context context, ArrayList<Note> notes) {
        super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Note note = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_note, parent, false);
        }

        TextView tvNom = convertView.findViewById(R.id.tvItemNom);
        TextView tvDate = convertView.findViewById(R.id.tvItemDate);

        tvNom.setText(note.getNom());
        tvDate.setText(note.getDate());

        if (note.getPriorite().equals("Haute")) {
            tvNom.setTextColor(Color.RED);
        } else if (note.getPriorite().equals("Moyenne")) {
            tvNom.setTextColor(Color.parseColor("#FFA500")); // Orange
        } else {
            tvNom.setTextColor(Color.parseColor("#008000")); // Green
        }

        return convertView;
    }
}