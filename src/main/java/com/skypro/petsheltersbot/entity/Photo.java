package com.skypro.petsheltersbot.entity;

import javax.persistence.*;

@Entity
@Table(name = "photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String mediaType;

    @Column(columnDefinition = "oid")
    private byte[] data;

    public Photo(long id, String mediaType, byte[] data) {
        this.id = id;
        this.mediaType = mediaType;
        this.data = data;
    }

    public Photo(String mediaType, byte[] data) {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}

