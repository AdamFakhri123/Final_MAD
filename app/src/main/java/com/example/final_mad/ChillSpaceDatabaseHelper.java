package com.example.final_mad;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ChillSpaceDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "chillspace.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_ARTIST = "artist";
    public static final String COLUMN_IS_FAVORITE = "is_favorite";

    private static final String CREATE_TABLE_SONGS =
            "CREATE TABLE " + TABLE_SONGS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT NOT NULL, " +
                    COLUMN_ARTIST + " TEXT, " +
                    COLUMN_IS_FAVORITE + " INTEGER DEFAULT 0);";

    public ChillSpaceDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SONGS);

        db.execSQL("INSERT INTO " + TABLE_SONGS + " (title, artist, is_favorite) VALUES ('Peaceful Piano', 'Calm Artist', 0)");
        db.execSQL("INSERT INTO " + TABLE_SONGS + " (title, artist, is_favorite) VALUES ('Ocean Waves', 'Nature Sounds', 0)");
        db.execSQL("INSERT INTO " + TABLE_SONGS + " (title, artist, is_favorite) VALUES ('Mindful Melody', 'Zen Beats', 0)");
        db.execSQL("INSERT INTO " + TABLE_SONGS + " (title, artist, is_favorite) VALUES ('Gentle Breeze', 'Relaxing Tunes', 0)");
        db.execSQL("INSERT INTO " + TABLE_SONGS + " (title, artist, is_favorite) VALUES ('Deep Focus', 'LoFi Studio', 0)");


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGS);
        onCreate(db);
    }
}
