import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {AuthenticationGuard} from "./guards/authentication.guard";
import {ProfileComponent} from "./components/profile/profile.component";
import {HomeComponent} from "./components/home/home.component";
import {SettingsComponent} from "./components/settings/settings.component";
import {RealisationComponent} from "./components/realisation/realisation.component";
import {ForbiddenPageComponent} from "./components/utilities/forbidden-page/forbidden-page.component";
import {NotFoundPageComponent} from "./components/utilities/not-found-page/not-found-page.component";
import {GradesComponent} from "./components/grades/grades.component";
import {ActivitiesComponent} from "./components/activities/activities.component";
import {StudentGuard} from "./guards/student.guard";
import {TeacherGuard} from "./guards/teacher.guard";
import {SubjectsComponent} from "./components/manage-school/subjects/subjects.component";
import {RealisationsComponent} from "./components/manage-school/realisations/realisations.component";
import {ClassesComponent} from "./components/manage-school/classes/classes.component";
import {UsersComponent} from "./components/manage-school/users/users.component";
import {LevelsComponent} from "./components/manage-school/levels/levels.component";
import {ManagementGuard} from "./guards/management.guard";

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
      },
      {
        path: "realisation/:id/grades",
        component: GradesComponent,
        canActivate: [AuthenticationGuard, StudentGuard]
      },
      {
        path: "realisation/:id/activities",
        component: ActivitiesComponent,
        canActivate: [AuthenticationGuard, TeacherGuard]
      },
      {
        path: "subjects",
        component: SubjectsComponent,
        canActivate: [AuthenticationGuard, ManagementGuard]
      },
      {
        path: "realisations",
        component: RealisationsComponent,
        canActivate: [AuthenticationGuard, ManagementGuard]
      },
      {
        path: "classes",
        component: ClassesComponent,
        canActivate: [AuthenticationGuard, ManagementGuard]
      },
      {
        path: "users",
        component: UsersComponent,
        canActivate: [AuthenticationGuard, ManagementGuard]
      },
      {
        path: "levels",
        component: LevelsComponent,
        canActivate: [AuthenticationGuard, ManagementGuard]
      },
      {
        path: "forbidden",
        component: ForbiddenPageComponent,
        canActivate: [AuthenticationGuard]
      },
      {
        path: "404",
        component: NotFoundPageComponent,
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
  {
    path: '**', redirectTo: '404'
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
