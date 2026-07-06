package com.llmc.stars.service;

import com.llmc.stars.model.Feedback;
import com.llmc.stars.model.dto.FeedbackDto;
import com.llmc.stars.repository.FeedbackRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeedbackServiceTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackService feedbackService;

    @Test
    void publicarFeedbackSalvaFeedbackComDadosDoDto() {
        FeedbackDto dto = new FeedbackDto(
                "Lucas",
                "lucas@email.com",
                "Maria",
                "@maria",
                "Atendimento",
                5,
                "Excelente atendimento"
        );

        feedbackService.publicarFeedback(dto);

        ArgumentCaptor<Feedback> feedbackCaptor = ArgumentCaptor.forClass(Feedback.class);
        verify(feedbackRepository).save(feedbackCaptor.capture());

        Feedback feedback = feedbackCaptor.getValue();
        assertThat(feedback.getNomeCriador()).isEqualTo("Lucas");
        assertThat(feedback.getEmailCriador()).isEqualTo("lucas@email.com");
        assertThat(feedback.getNomeAvaliado()).isEqualTo("Maria");
        assertThat(feedback.getInstagramAvaliado()).isEqualTo("@maria");
        assertThat(feedback.getCategoria()).isEqualTo("Atendimento");
        assertThat(feedback.getNota()).isEqualTo(5);
        assertThat(feedback.getComentario()).isEqualTo("Excelente atendimento");
    }

    @Test
    void getAllFeedbackRetornaFeedbacksDoRepositorio() {
        List<Feedback> feedbacks = List.of(
                new Feedback("Lucas", "lucas@email.com", "Maria", "@maria", "Atendimento", 5, "Excelente"),
                new Feedback("Ana", "ana@email.com", "Joao", "@joao", "Produto", 4, "Bom")
        );
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Feedback> feedbackPage = new PageImpl<>(feedbacks, pageable, feedbacks.size());
        when(feedbackRepository.findAll(pageable)).thenReturn(feedbackPage);

        Page<Feedback> result = feedbackService.getAllFeedback(10, 0);

        assertThat(result).isEqualTo(feedbackPage);
        verify(feedbackRepository).findAll(pageable);
    }

    @Test
    void searchFeedbackBuscaPorNomeAvaliado() {
        List<Feedback> feedbacks = List.of(
                new Feedback("Lucas", "lucas@email.com", "Maria Silva", "@maria", "Atendimento", 5, "Excelente")
        );
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Feedback> feedbackPage = new PageImpl<>(feedbacks, pageable, feedbacks.size());
        when(feedbackRepository.findFeedbackByNomeAvaliadoContaining("Maria", pageable)).thenReturn(feedbackPage);

        Page<Feedback> result = feedbackService.searchFeedback("Maria", 10, 0);

        assertThat(result).isEqualTo(feedbackPage);
        verify(feedbackRepository).findFeedbackByNomeAvaliadoContaining("Maria", pageable);
    }
}
