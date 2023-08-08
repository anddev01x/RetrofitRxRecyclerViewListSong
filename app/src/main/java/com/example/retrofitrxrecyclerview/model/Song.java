package com.example.retrofitrxrecyclerview.model;

public class Song {
    private String id;
    private String title;
    private String album;
    private String artist;
    private String source;
    private String image;
    private int duration;

    public Song(String id, String title, String album, String artist, String source, String image, int duration) {
        this.id = id;
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.source = source;
        this.image = image;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", album='" + album + '\'' +
                ", artist='" + artist + '\'' +
                ", source='" + source + '\'' +
                ", image='" + image + '\'' +
                ", duration=" + duration +
                '}';
    }
}
