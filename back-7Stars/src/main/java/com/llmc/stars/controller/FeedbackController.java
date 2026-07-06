package com.llmc.stars.controller;

import com.llmc.stars.model.Feedback;
import com.llmc.stars.model.dto.FeedbackDto;
import com.llmc.stars.service.FeedbackService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }


    @PostMapping("/feedback")
    private ResponseEntity<?> publicarFeedback(@RequestBody FeedbackDto feedbackDto){
        feedbackService.publicarFeedback(feedbackDto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/feedback")
    private Page<Feedback> getAllFeedback(@RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "0")int page){
        return feedbackService.getAllFeedback(size, page);
    }

    @GetMapping("/feedback/search")
    private Page<Feedback> searchFeedback(@RequestParam String nome,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "0")int page) {
        return feedbackService.searchFeedback(nome, size, page);
    }

}
