import { Component } from '@angular/core';


export interface Feedback {
  id: number;
  nomeCriador?: string;
  emailCriador?: string;
  nomeAvaliado?: string;
  instagramAvaliado?: string;
  categoria?: string;
  nota: number;
  comentario?: string;
}

@Component({
  selector: 'app-feedback',
  imports: [],
  templateUrl: './feedback.html',
  styleUrl: './feedback.css',
})
export class Feedback {}
