package com.example.final_mad;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class MoodTrackerActivity extends AppCompatActivity {

    RadioGroup radioGroupMood;
    Button btnSaveMood;
    ListView listViewMoods;
    ArrayAdapter<String> adapter;
    ArrayList<String> moodList;
    SharedPreferences sharedPreferences;

    static final String PREFS_NAME = "MoodPrefs";
    static final String KEY_MOODS = "MoodHistory";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_tracker);

        radioGroupMood = findViewById(R.id.radioGroupMood);
        btnSaveMood = findViewById(R.id.btnSaveMood);
        listViewMoods = findViewById(R.id.listViewMoods);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        Set<String> moodSet = sharedPreferences.getStringSet(KEY_MOODS, new HashSet<>());
        moodList = new ArrayList<>(moodSet);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, moodList);
        listViewMoods.setAdapter(adapter);

        EditText editTextNote = findViewById(R.id.editTextNote);

        btnSaveMood.setOnClickListener(v -> {
            int selectedId = radioGroupMood.getCheckedRadioButtonId();

            if (selectedId == -1) {
                Toast.makeText(this, "Please select a mood", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedRadio = findViewById(selectedId);
            String moodText = selectedRadio.getText().toString();

            String note = editTextNote.getText().toString().trim();

            // Get current date/time
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());

            // Combine into formatted entry
            String entry = moodText + " - " + timeStamp;
            if (!note.isEmpty()) {
                entry += "\n" + note;
            }

            moodList.add(entry);
            adapter.notifyDataSetChanged();

            // Save to SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putStringSet(KEY_MOODS, new HashSet<>(moodList));
            editor.apply();

            // Clear note and selection
            radioGroupMood.clearCheck();
            editTextNote.setText("");

            Toast.makeText(this, "Mood saved!", Toast.LENGTH_SHORT).show();
        });

    }
}
