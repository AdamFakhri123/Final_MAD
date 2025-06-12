package com.example.final_mad;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;


import java.util.List;

public class SongAdapter extends ArrayAdapter<Song> {

    private ChillSpaceDatabaseHelper dbHelper;

    public SongAdapter(Context context, List<Song> songs, ChillSpaceDatabaseHelper dbHelper) {
        super(context, 0, songs);
        this.dbHelper = dbHelper;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Song song = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_song, parent, false);
        }

        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        TextView tvArtist = convertView.findViewById(R.id.tvArtist);
        ImageView imgHeart = convertView.findViewById(R.id.imgHeart);

        tvTitle.setText(song.title);
        tvArtist.setText(song.artist);
        imgHeart.setImageResource(song.isFavorite ? R.drawable.ic_heart_filled : R.drawable.ic_heart_outline);

        imgHeart.setOnClickListener(v -> {
            song.isFavorite = !song.isFavorite;
            updateFavoriteInDb(song.id, song.isFavorite);
            notifyDataSetChanged();
        });

        return convertView;
    }

    private void updateFavoriteInDb(long songId, boolean isFavorite) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ChillSpaceDatabaseHelper.COLUMN_IS_FAVORITE, isFavorite ? 1 : 0);
        db.update(ChillSpaceDatabaseHelper.TABLE_SONGS, values,
                ChillSpaceDatabaseHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(songId)});
    }
}
