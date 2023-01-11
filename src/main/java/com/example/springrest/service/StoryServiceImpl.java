package com.example.springrest.service;

import com.example.springrest.model.Story;
import com.example.springrest.repository.StoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StoryServiceImpl implements StoryService {
    private final StoryRepository storyRepository;

    public StoryServiceImpl(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @Override
    public Story addStory(Story story) {
        Optional<Story> storyToAdd = storyRepository.findByTitle(story.getTitle());
        if(storyToAdd.isPresent()){
            throw new RuntimeException("Story already exists with this title.");
        }
        story.setDate(LocalDateTime.now());
        story.setVip(story.getVip());
        return storyRepository.save(story);
    }

    @Override
    public List<Story> getStoriesByWriter(String writerName) {
        List<Story> stories = storyRepository.findByWriter(writerName);
        if(stories.isEmpty()){
            throw new RuntimeException("No story to show.");
        }
        return stories;
    }

    @Override
    public List<Story> findAllNonVipStory() {
        List<Story> storyList = storyRepository.findAllNonVip();
        if(storyList.isEmpty()) {
            throw new RuntimeException("No vip story to show.");
        }
        return storyList;
    }

    @Override
    public List<Story> findAllVipStory() {
        List<Story> storyList = storyRepository.findAllVip();
        if(storyList.isEmpty()) {
            throw new RuntimeException("No vip story to show.");
        }
        return storyList;
    }
}
