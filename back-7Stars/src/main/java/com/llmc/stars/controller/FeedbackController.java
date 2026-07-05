package com.llmc.stars.controller;

import com.llmc.stars.model.Feedback;
import com.llmc.stars.model.dto.FeedbackDto;
import com.llmc.stars.service.FeedbackService;
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
    private List<Feedback> getAllFeedback(){
        return feedbackService.getAllFeedback();
    }

}
