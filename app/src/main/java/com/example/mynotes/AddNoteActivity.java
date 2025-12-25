package com.example.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        EditText editNom = findViewById(R.id.editNom);
        EditText editDesc = findViewById(R.id.editDescription);
        EditText editDate = findViewById(R.id.editDate);
        Spinner spinner = findViewById(R.id.spinnerPriorite);
        Button btnSave = findViewById(R.id.btnSave);

        String[] priorities = {"Basse", "Moyenne", "Haute"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                priorities
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = editNom.getText().toString();
                String desc = editDesc.getText().toString();
                String date = editDate.getText().toString();
                String priority = spinner.getSelectedItem().toString();

                Intent intent = new Intent();
                intent.putExtra("nom", nom);
                intent.putExtra("description", desc);
                intent.putExtra("date", date);
                intent.putExtra("priorite", priority);

                setResult(RESULT_OK, intent);
                finish(); // Closes this activity and goes back
            }
        });
    }
}