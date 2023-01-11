package com.example.springrest.controller;

import com.example.springrest.helperclass.Writer;
import com.example.springrest.model.Story;
import com.example.springrest.service.StoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restapi/v1/story")
public class StoryController {
    private final StoryServiceImpl storyService;

    public StoryController(StoryServiceImpl storyService) {
        this.storyService = storyService;
    }

    //VIP only
    @GetMapping("/getbywriter")
    public ResponseEntity<List<Story>> findByWriter(@RequestBody Writer writer) {
        List<Story> stories = storyService.getStoriesByWriter(writer.getWriter());
        return new ResponseEntity<>(stories, HttpStatus.OK);
    }
    //VIP only
    @GetMapping("/getvipstories")
    public ResponseEntity<List<Story>> getVipStories() {
        List<Story> vipStories = storyService.findAllVipStory();
        return new ResponseEntity<>(vipStories, HttpStatus.OK);
    }
    @GetMapping("/getstories")
    public ResponseEntity<List<Story>> getStories() {
        List<Story> stories = storyService.findAllNonVipStory();
        return new ResponseEntity<>(stories, HttpStatus.OK);
    }
    //ADMIN only
    @PostMapping("/addstory")
    public ResponseEntity<Story> addStory(@Valid @RequestBody Story story) {
        storyService.addStory(story);
        return new ResponseEntity<>(story, HttpStatus.CREATED);
    }
}
