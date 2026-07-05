import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { FeedbackService } from '../../services/feedback';
import { Router } from '@angular/router';
import { NgClass, NgFor } from '@angular/common';

@Component({
  selector: 'app-cadastro',
  standalone: true,
  imports: [FormsModule, NgClass, NgFor],
  templateUrl: './cadastro.html',
  styleUrl: './cadastro.css',
})
export class CadastroComponent {
  
  stars = [1, 2, 3, 4, 5, 6, 7];

  feedback = {
    nomeCriador: '',
    emailCriador: '',
    nomeAvaliado: '',
    instagramAvaliado: '',
    categoria: '',
    nota: 0,
    comentario: ''
  }

  constructor(private feedbackService: FeedbackService, private router: Router) { }

    selecionarNota  (nota: number): void {
    this.feedback.nota = nota;
  }

  cadastrarFeedback(): void {
    console.log('Feedback antes do POST:', this.feedback);
    this.feedbackService.cadastrar(this.feedback).subscribe({
      next: () => {
        alert('Feedback cadastrado com sucesso!');

        this.feedback = {
          nomeCriador: '',
          emailCriador: '',
          nomeAvaliado: '',
          instagramAvaliado: '',
          categoria: '',
          nota: 0,
          comentario: ''
        };
        this.router.navigate(['/']);
      },
      error: (error) => {
        console.error('Erro ao cadastrar feedback:', error);
      }
    });
  }



}
