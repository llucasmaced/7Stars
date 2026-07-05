package com.llmc.stars.service;

import com.llmc.stars.model.Feedback;
import com.llmc.stars.model.dto.FeedbackDto;
import com.llmc.stars.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }


    public void publicarFeedback(FeedbackDto data){
        Feedback feedback = new Feedback(data.nomeCriador(), data.emailCriador(), data.nomeAvaliado(), data.instagramAvaliado(), data.categoria(), data.nota(), data.comentario());
        feedbackRepository.save(feedback);
    }

    public List<Feedback> getAllFeedback(){
        return feedbackRepository.findAll();
    }

    public List<Feedback> searchFeedback(String nomeSearch){
        return feedbackRepository.findFeedbackByNomeAvaliadoContaining(nomeSearch);
    }

}
