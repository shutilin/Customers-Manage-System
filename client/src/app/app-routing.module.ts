import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';

import {HomeComponent} from './components/home/home.component';

const appRoutes: Routes = [
  {
    path: '',
    component: HomeComponent // The Default Route
  },
  { path: '**', component: HomeComponent } // The "Catch-All" Route
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(appRoutes)],
  providers: [],
  bootstrap: [],
  exports: [RouterModule]
})
export class AppRoutingModel { }
