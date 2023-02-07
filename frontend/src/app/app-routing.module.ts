import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {AuthenticationGuard} from "./guards/authentication.guard";
import {ProfileComponent} from "./components/profile/profile.component";
import {HomeComponent} from "./components/home/home.component";

const routes: Routes = [
  {
    path: "",
    component: HomeComponent,
    canActivate: [AuthenticationGuard],
    children: [
      {
        path: "profile",
        component: ProfileComponent,
        canActivate: [AuthenticationGuard]
      }
    ]
  },
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "register",
    component: RegisterComponent
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
