import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {AuthenticationGuard} from "./guards/authentication.guard";
import {ProfileComponent} from "./components/profile/profile.component";
import {HomeComponent} from "./components/home/home.component";
import {SettingsComponent} from "./components/settings/settings.component";
import {RealisationComponent} from "./components/realisation/realisation.component";

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
      },
      {
        path: "settings",
        component: SettingsComponent,
        canActivate: [AuthenticationGuard]
      },
      {
        path: "realisation/:id",
        component: RealisationComponent,
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
