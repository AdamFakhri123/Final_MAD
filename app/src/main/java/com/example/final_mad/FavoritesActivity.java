package com.example.final_mad;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    ListView favoriteListView;
    ChillSpaceDatabaseHelper dbHelper;
    ArrayList<Song> favoriteSongs;
    SongAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        favoriteListView = findViewById(R.id.favoriteListView);
        dbHelper = new ChillSpaceDatabaseHelper(this);

        loadFavoriteSongs();
    }

    private void loadFavoriteSongs() {
        favoriteSongs = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                ChillSpaceDatabaseHelper.TABLE_SONGS,
                null,
                ChillSpaceDatabaseHelper.COLUMN_IS_FAVORITE + "=?",
                new String[]{"1"},
                null, null, null
        );

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(ChillSpaceDatabaseHelper.COLUMN_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(ChillSpaceDatabaseHelper.COLUMN_TITLE));
            String artist = cursor.getString(cursor.getColumnIndexOrThrow(ChillSpaceDatabaseHelper.COLUMN_ARTIST));
            int fav = cursor.getInt(cursor.getColumnIndexOrThrow(ChillSpaceDatabaseHelper.COLUMN_IS_FAVORITE));
            favoriteSongs.add(new Song(id, title, artist, fav == 1));
        }
        cursor.close();

        adapter = new SongAdapter(this, favoriteSongs, dbHelper);
        favoriteListView.setAdapter(adapter);
    }
}
