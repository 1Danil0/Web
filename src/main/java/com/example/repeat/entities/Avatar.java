package com.example.repeat.entities;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Entity
@Table(name = "avatars")
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "originalName")
    private String originalName;
    @Column(name = "size")
    private long size;
    @Column(name = "contentType")
    private String contentType;
    @Lob
    @Column(name = "bytes", columnDefinition = "longblob")
    private byte[] bytes;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Avatar() {
    }

    public Avatar(String name, String originalName, long size, String contentType,
                  byte[] bytes, User user) {
        this.name = name;
        this.originalName = originalName;
        this.size = size;
        this.contentType = contentType;
        this.bytes = bytes;
        this.user = user;
    }

    public static Avatar toImage(MultipartFile file) throws IOException {
        Avatar avatar = new Avatar();
        avatar.setName(file.getName());
        avatar.setOriginalName(file.getOriginalFilename());
        avatar.setBytes(file.getBytes());
        avatar.setContentType(file.getContentType());
        avatar.setSize(file.getSize());
        return avatar;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
