package com.desarrollo.autores.Models;

public class DataBook {
    private String id;
    private String title;
    private String publisher;
    private String thumbnail;
    private String authors;

    public DataBook(String id, String title, String publisher, String thumbnail, String authors) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.thumbnail = thumbnail;
        this.authors = authors;
    }

    public DataBook() {}

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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }
}
