package com.example.wheelworld.models;

import jakarta.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;
    @Column(length = 500000000)
    private byte[] picByte;

//    @ManyToOne
//    @JsonIgnore
//    private Annonce annonce;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "annonce_id", nullable = false)
//    private Annonce annonce;

    public Image(String name, String type, byte[] picByte) {

        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }

    public Image() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPicByte() {
        return picByte;
    }

    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }

//    public Annonce getAnnonce() {
//        return annonce;
//    }
//
//    public void setAnnonce(Annonce annonce) {
//        this.annonce = annonce;
//    }
}
