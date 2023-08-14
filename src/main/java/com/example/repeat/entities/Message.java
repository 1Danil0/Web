package com.example.repeat.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "text", columnDefinition = "text")
    private String text;
    @Column(name = "tag")
    private String tag;
    @Column(name = "date")
    private LocalDateTime creationDate;
    @Column(name = "price")
    private int price;
    @Column(name = "city")
    private String city;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "message")
    private Set<Image> images;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private User user;
    private long previewImage;

    public Message() {
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    @PrePersist
    public void init(){
        creationDate = LocalDateTime.now();
    }

    public Message(String text, String tag, int price) {
        this.text = text;
        this.tag = tag;
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addImage(Image image){
        if(images == null){
            images = new HashSet<>();
        }
        images.add(image);
        image.setMessage(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getPreviewImage() {
        return previewImage;
    }

    public void setPreviewImage(long previewImage) {
        this.previewImage = previewImage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
