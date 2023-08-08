package com.example.retrofitrxrecyclerview.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListSong {
    @SerializedName("song")
    private List<Song> songs;

    public ListSong(List<Song> songs) {
        this.songs = songs;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}

