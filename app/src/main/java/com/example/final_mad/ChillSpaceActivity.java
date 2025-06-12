package com.example.final_mad;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ChillSpaceActivity extends AppCompatActivity {

    ListView songListView;
    ChillSpaceDatabaseHelper dbHelper;
    ArrayList<Song> songList;
    SongAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chill_space);

        songListView = findViewById(R.id.songListView);
        dbHelper = new ChillSpaceDatabaseHelper(this);

        loadSongs();

        Button btnViewFavorites = findViewById(R.id.btnViewFavorites);
        btnViewFavorites.setOnClickListener(v -> {
            Intent intent = new Intent(ChillSpaceActivity.this, FavoritesActivity.class);
            startActivity(intent);
        });

    }

    private void loadSongs() {
        songList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(ChillSpaceDatabaseHelper.TABLE_SONGS,
                null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(ChillSpaceDatabaseHelper.COLUMN_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(ChillSpaceDatabaseHelper.COLUMN_TITLE));
            String artist = cursor.getString(cursor.getColumnIndexOrThrow(ChillSpaceDatabaseHelper.COLUMN_ARTIST));
            int fav = cursor.getInt(cursor.getColumnIndexOrThrow(ChillSpaceDatabaseHelper.COLUMN_IS_FAVORITE));
            songList.add(new Song(id, title, artist, fav == 1));
        }
        cursor.close();

        adapter = new SongAdapter(this, songList, dbHelper);
        songListView.setAdapter(adapter);
    }
}
