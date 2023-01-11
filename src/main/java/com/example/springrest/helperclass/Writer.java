package com.example.springrest.helperclass;

import org.springframework.stereotype.Component;

@Component
public class Writer {
    private String writer;

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
