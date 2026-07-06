package com.llmc.stars.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.llmc.stars.model.Feedback;
import com.llmc.stars.model.dto.FeedbackDto;
import com.llmc.stars.service.FeedbackService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
        when(feedbackService.getAllFeedback()).thenReturn(List.of(
                new Feedback("Lucas", "lucas@email.com", "Maria", "@maria", "Atendimento", 5, "Excelente")
        ));

        mockMvc.perform(get("/api/feedback"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nomeCriador").value("Lucas"))
                .andExpect(jsonPath("$[0].emailCriador").value("lucas@email.com"))
                .andExpect(jsonPath("$[0].nomeAvaliado").value("Maria"))
                .andExpect(jsonPath("$[0].instagramAvaliado").value("@maria"))
                .andExpect(jsonPath("$[0].categoria").value("Atendimento"))
                .andExpect(jsonPath("$[0].nota").value(5))
                .andExpect(jsonPath("$[0].comentario").value("Excelente"));

        verify(feedbackService).getAllFeedback();
    }

    @Test
    void searchFeedbackRetornaFeedbacksFiltradosPorNome() throws Exception {
        when(feedbackService.searchFeedback("Maria")).thenReturn(List.of(
                new Feedback("Lucas", "lucas@email.com", "Maria Silva", "@maria", "Atendimento", 5, "Excelente")
        ));

        mockMvc.perform(get("/api/feedback/search").param("nome", "Maria"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nomeAvaliado").value("Maria Silva"));

        verify(feedbackService).searchFeedback("Maria");
    }
}
