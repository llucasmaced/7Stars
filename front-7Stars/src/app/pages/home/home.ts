import { Component, OnInit, signal } from '@angular/core';
import { Feedback } from '../../models/feedback/feedback';
import { FeedbackService } from '../../services/feedback';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [NgFor],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class HomeComponent implements OnInit {

  feedbacks = signal<Feedback[]>([]);


  page = 0;
  size = 9;
  totalPages = 0;

  constructor(private feedbackService: FeedbackService) { }

  ngOnInit(): void {
    this.carregarFeedbacks();
  }

  carregarFeedbacks(): void {
  if (this.termoBusca.trim()) {
    this.feedbackService.buscar(this.termoBusca, this.page, this.size).subscribe({
      next: (response) => {
        this.feedbacks.set(response.content);
        this.totalPages = response.totalPages;
      }
    });
    return;
  }

  this.feedbackService.listarPaginado(this.page, this.size).subscribe({
    next: (response) => {
      this.feedbacks.set(response.content);
      this.totalPages = response.totalPages;
    }
  });
  }

  proximaPagina(): void {
    if (this.page < this.totalPages - 1) {
      this.page++;
      this.carregarFeedbacks();
    }
  }

  paginaAnterior(): void {
    if (this.page > 0) {
      this.page--;
      this.carregarFeedbacks();
    }
  }


  getStars(nota: number): number[] {
    return Array(nota).fill(0);
  }

  termoBusca = '';

  buscar(nome: string): void {
    this.termoBusca = nome;
    this.page = 0; 

    if (!nome.trim()) { 
      this.carregarFeedbacks();
      return;
    }

    this.feedbackService.buscar(nome, this.page, this.size).subscribe({
      next: (response) => {

        this.feedbacks.set(response.content);
        this.totalPages = response.totalPages; 
      },
      error: (erro) => {
        console.error('Erro ao buscar feedbacks:', erro);
      }
    });

  }
}
