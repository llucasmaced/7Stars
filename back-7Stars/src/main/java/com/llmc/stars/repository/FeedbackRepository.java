package com.llmc.stars.repository;

import com.llmc.stars.model.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    public Page<Feedback> findFeedbackByNomeAvaliadoContaining(String nomeAvaliado, Pageable pageable);

    public Page<Feedback> findAll(Pageable pageable);

}
