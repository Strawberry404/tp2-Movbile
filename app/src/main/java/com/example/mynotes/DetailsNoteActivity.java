package com.example.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DetailsNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_note);

        TextView tvNom = findViewById(R.id.tvDetailNom);
        TextView tvDate = findViewById(R.id.tvDetailDate);
        TextView tvPriorite = findViewById(R.id.tvDetailPriorite);
        TextView tvDesc = findViewById(R.id.tvDetailDesc);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnEdit = findViewById(R.id.btnEdit);

        Intent intent = getIntent();
        if (intent != null) {
            String nom = intent.getStringExtra("nom");
            String date = intent.getStringExtra("date");
            String priorite = intent.getStringExtra("priorite");
            String description = intent.getStringExtra("description");

            tvNom.setText(nom);
            tvDate.setText(date);
            tvPriorite.setText(priorite);
            tvDesc.setText(description);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailsNoteActivity.this, "Modifier feature coming soon!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}