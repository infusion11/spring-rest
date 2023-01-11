package com.example.springrest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

@Entity
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Name cannot be empty.")
    private String writer;
    private LocalDateTime date;
    @NotEmpty(message = "Title cannot be empty.")
    private String title;
    @Column(nullable = false, length = 1500)
    private String text;
    private Boolean isVip;

    public Story() {
    }

    public Story(Integer id, String writer, LocalDateTime date, String title, String text, Boolean isVip) {
        this.id = id;
        this.writer = writer;
        this.date = date;
        this.title = title;
        this.text = text;
        this.isVip = isVip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getVip() {
        return isVip;
    }

    public void setVip(Boolean vip) {
        isVip = vip;
    }

    @Override
    public String toString() {
        return "Story{" +
                "id=" + id +
                ", writer='" + writer + '\'' +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", isVip=" + isVip +
                '}';
    }
}
