package com.example.final_mad;

public class Song {
    public long id;
    public String title;
    public String artist;
    public boolean isFavorite;

    public Song(long id, String title, String artist, boolean isFavorite) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.isFavorite = isFavorite;
    }
}

