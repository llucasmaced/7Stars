package com.llmc._stars.service;

import com.llmc._stars.model.Feedback;
import com.llmc._stars.model.dto.FeedbackDto;
import com.llmc._stars.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }


    public void publicarFeedback(FeedbackDto data){
        Feedback feedback = new Feedback(data.nomeCriador(), data.emailCriador(), data.nomeAvaliado(), data.InstagramAvaliado(), data.Categoria(), data.nota(), data.comentario());
        feedbackRepository.save(feedback);
    }

    public List<Feedback> getAllFeedback(){
        return feedbackRepository.findAll();
    }

}
