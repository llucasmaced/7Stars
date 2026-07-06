import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Feedback } from '../models/feedback/feedback';

@Injectable({
    providedIn: 'root'
})  
export class FeedbackService {

    private readonly apiUrl = 'http://localhost:8080/api/feedback';

    constructor(private http: HttpClient) { }

    listar(): Observable<Feedback[]>{
        return this.http.get<Feedback[]>(this.apiUrl);
    }

    cadastrar(feedback: any): Observable<any> {
        return this.http.post<any>(this.apiUrl, feedback);
    }

    buscar(nome: string){
        return this.http.get<Feedback[]>(`${this.apiUrl}/search?nome=${nome}`);
    }

}
