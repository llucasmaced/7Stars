package com.llmc.stars.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.llmc.stars.model.Feedback;
import com.llmc.stars.model.dto.FeedbackDto;
import com.llmc.stars.service.FeedbackService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FeedbackController.class)
class FeedbackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private FeedbackService feedbackService;

    @Test
    void publicarFeedbackRetornaCreated() throws Exception {
        FeedbackDto dto = new FeedbackDto(
                "Lucas",
                "lucas@email.com",
                "Maria",
                "@maria",
                "Atendimento",
                5,
                "Excelente atendimento"
        );

        mockMvc.perform(post("/api/feedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(content().string(""));

        verify(feedbackService).publicarFeedback(any(FeedbackDto.class));
    }

    @Test
    void getAllFeedbackRetornaFeedbacks() throws Exception {
        PageRequest pageable = PageRequest.of(0, 10);
        when(feedbackService.getAllFeedback(10, 0)).thenReturn(new PageImpl<>(List.of(
                new Feedback("Lucas", "lucas@email.com", "Maria", "@maria", "Atendimento", 5, "Excelente")
        ), pageable, 1));

        mockMvc.perform(get("/api/feedback"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nomeCriador").value("Lucas"))
                .andExpect(jsonPath("$.content[0].emailCriador").value("lucas@email.com"))
                .andExpect(jsonPath("$.content[0].nomeAvaliado").value("Maria"))
                .andExpect(jsonPath("$.content[0].instagramAvaliado").value("@maria"))
                .andExpect(jsonPath("$.content[0].categoria").value("Atendimento"))
                .andExpect(jsonPath("$.content[0].nota").value(5))
                .andExpect(jsonPath("$.content[0].comentario").value("Excelente"));

        verify(feedbackService).getAllFeedback(10, 0);
    }

    @Test
    void searchFeedbackRetornaFeedbacksFiltradosPorNome() throws Exception {
        PageRequest pageable = PageRequest.of(0, 10);
        when(feedbackService.searchFeedback("Maria", 10, 0)).thenReturn(new PageImpl<>(List.of(
                new Feedback("Lucas", "lucas@email.com", "Maria Silva", "@maria", "Atendimento", 5, "Excelente")
        ), pageable, 1));

        mockMvc.perform(get("/api/feedback/search").param("nome", "Maria"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nomeAvaliado").value("Maria Silva"));

        verify(feedbackService).searchFeedback("Maria", 10, 0);
    }
}
