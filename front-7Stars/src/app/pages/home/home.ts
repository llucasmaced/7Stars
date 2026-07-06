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

  constructor(private feedbackService: FeedbackService) { }

  ngOnInit(): void {
    this.feedbackService.listar().subscribe({
      next: (dados) => {
        console.log('Dados recebidos:', dados);
        this.feedbacks.set(dados);
      },
      error: (erro) => {
        console.error('Erro ao listar feedbacks:', erro);
      }  
    });
  }  


  getStars(nota: number): number[] {
    return Array(nota).fill(0);
    }

  buscar(nome: string): void {
    this.feedbackService.buscar(nome).subscribe({
      next: (dados) => {
        console.log('Dados recebidos da busca:', dados);
        this.feedbacks.set(dados);
      },
      error: (erro) => {
        console.error('Erro ao buscar feedbacks:', erro);
      }
    });
  }
}
