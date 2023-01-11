package com.example.springrest;

import com.example.springrest.model.Story;
import com.example.springrest.repository.StoryRepository;
import com.example.springrest.service.StoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StoryServiceTest {

    @Mock
    private StoryServiceImpl storyService;

    @Mock
    StoryRepository storyRepository;


    @BeforeEach
    void init() {
        storyService = new StoryServiceImpl(storyRepository);
    }

    @Test
    public void addStoryTest() {
        Story newStory = new Story(1, "Test Writer", LocalDateTime.now(), "Test Title", "Test Text", false);
        when(storyRepository.save(Mockito.any())).thenReturn(newStory);

        Story story = storyRepository.save(newStory);

        Mockito.verify(storyRepository).save(Mockito.any());
        System.out.println("Saved story title is: " + story.getTitle());
    }

    @Test
    public void getStoriesTest() {
        Story newStory = new Story(1, "Test Writer", LocalDateTime.now(), "Test Title", "Test Text", false);
        when(storyRepository.findAllNonVip()).thenReturn(asList(newStory));

        List<Story> nonVipStoryList = storyService.findAllNonVipStory();
        System.out.println("newStory: " + newStory);
        System.out.println("Story list: " + nonVipStoryList);
        Mockito.verify(storyRepository).findAllNonVip();
        Assertions.assertEquals(newStory, nonVipStoryList.get(0));
    }

    @Test
    public void getVipStoriesTest() {
        Story newStory = new Story(1, "Test Writer", LocalDateTime.now(), "Test Title", "Test Text", false);
        when(storyRepository.findAllVip()).thenReturn(asList(newStory));

        List<Story> vipStoryList = storyService.findAllVipStory();
        System.out.println("newStory: " + newStory);
        System.out.println("Story list: " + vipStoryList);
        Mockito.verify(storyRepository).findAllVip();
        Assertions.assertEquals(newStory, vipStoryList.get(0));
    }

    @Test
    public void getStoriesByWriter() {
        Story newStory = new Story(1, "Test Writer", LocalDateTime.now(), "Test Title", "Test Text", false);
        String writer = "Test Writer";
        when(storyRepository.findByWriter(Mockito.any())).thenReturn(asList(newStory));

        List<Story> storiesByWriterList = storyService.getStoriesByWriter(Mockito.any());
        System.out.println("newStory: " + newStory);
        System.out.println("Story list: " + storiesByWriterList);
        Mockito.verify(storyRepository).findByWriter(Mockito.any());
        Assertions.assertEquals(newStory, storiesByWriterList.get(0));
    }

}
