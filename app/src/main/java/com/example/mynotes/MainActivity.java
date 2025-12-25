package com.example.mynotes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mynotes.models.Note;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Note> notes;
    NoteAdapter adapter;

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        String nom = data.getStringExtra("nom");
                        String desc = data.getStringExtra("description");
                        String date = data.getStringExtra("date");
                        String prio = data.getStringExtra("priorite");

                        Note newNote = new Note(nom, desc, date, prio);
                        notes.add(newNote);
                        adapter.notifyDataSetChanged();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnCamera = findViewById(R.id.btnCamera);

        notes = new ArrayList<>();
        notes.add(new Note("Exemple Note", "Ceci est un test", "25/12/2025", "Moyenne"));

        adapter = new NoteAdapter(this, notes);
        listView.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            mStartForResult.launch(intent);
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Note selectedNote = notes.get(position);
            Intent intent = new Intent(MainActivity.this, DetailsNoteActivity.class);
            intent.putExtra("nom", selectedNote.getNom());
            intent.putExtra("description", selectedNote.getDescription());
            intent.putExtra("date", selectedNote.getDate());
            intent.putExtra("priorite", selectedNote.getPriorite());
            startActivity(intent);
        });

        // UPDATED: Now calls the screenshot method
        btnCamera.setText("Capture d'écran");
        btnCamera.setOnClickListener(v -> takeScreenshotAndOpen());
    }

    private void takeScreenshotAndOpen() {
        View rootView = getWindow().getDecorView().getRootView();
        rootView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);

        try {
            File cachePath = new File(getCacheDir(), "images");
            cachePath.mkdirs();
            File filePath = new File(cachePath, "screenshot.png");

            FileOutputStream stream = new FileOutputStream(filePath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.close();

            Intent intent = new Intent(MainActivity.this, CameraActivity.class);
            intent.putExtra("imagePath", filePath.getAbsolutePath());
            startActivity(intent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}