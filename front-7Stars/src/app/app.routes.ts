import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home';
import { CadastroComponent } from './pages/cadastro/cadastro';

export const routes: Routes = [
    {
        path: '',
        component: HomeComponent
    },
    {
        path: 'avaliar',
        component: CadastroComponent
    }


];
