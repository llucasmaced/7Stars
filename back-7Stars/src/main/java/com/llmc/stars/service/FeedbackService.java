package com.llmc.stars.service;

import com.llmc.stars.model.Feedback;
import com.llmc.stars.model.dto.FeedbackDto;
import com.llmc.stars.repository.FeedbackRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Feedback> getAllFeedback(int size, int page){
        Pageable pageable = (Pageable) PageRequest.of(page, size);
        return feedbackRepository.findAll(pageable);
    }

    public Page<Feedback> searchFeedback(String nomeSearch, int size, int page){
        Pageable pageable = (Pageable) PageRequest.of(page, size);
        return feedbackRepository.findFeedbackByNomeAvaliadoContaining(nomeSearch, pageable);
    }

}
