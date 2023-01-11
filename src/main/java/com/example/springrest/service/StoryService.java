package com.example.springrest.service;

import com.example.springrest.model.Story;

import java.time.LocalDateTime;
import java.util.List;

public interface StoryService {
    Story addStory(Story story);
    List<Story> getStoriesByWriter(String writerName);
    List<Story> findAllNonVipStory();
    List<Story> findAllVipStory();
}
