package com.llmc.stars.repository;

import com.llmc.stars.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    public List<Feedback> findFeedbackByNomeAvaliadoContaining(String nomeAvaliado);

}
