import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {AuthenticationGuard} from "./guards/authentication.guard";
import {OwnProfileComponent} from "./components/own-profile/own-profile.component";
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
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {UserProfileComponent} from "./components/user-profile/user-profile.component";

const routes: Routes = [
  {
    path: "",
    component: HomeComponent,
    canActivate: [AuthenticationGuard],
    children: [
      {
        path: "profile",
        component: OwnProfileComponent,
        canActivate: [AuthenticationGuard],
        data: {
          title: "Profile"
        },
      },
      {
        path: "profile/:id",
        component: UserProfileComponent,
        canActivate: [AuthenticationGuard],
        data: {
          title: "Profile"
        },
      },
      {
        path: "dashboard",
        component: DashboardComponent,
        canActivate: [AuthenticationGuard],
        data: {
          title: "Dashboard"
        },
      },
      {
        path: "settings",
        component: SettingsComponent,
        canActivate: [AuthenticationGuard],
        data: {
          title: "Settings"
        },
      },
      {
        path: "realisation/:id",
        component: RealisationComponent,
        canActivate: [AuthenticationGuard],
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
        canActivate: [AuthenticationGuard, ManagementGuard],
        data: {
          title: "Subjects"
        },
      },
      {
        path: "realisations",
        component: RealisationsComponent,
        canActivate: [AuthenticationGuard, ManagementGuard],
        data: {
          title: "Realisations"
        },
      },
      {
        path: "classes",
        component: ClassesComponent,
        canActivate: [AuthenticationGuard, ManagementGuard],
        data: {
          title: "Classes"
        },
      },
      {
        path: "users",
        component: UsersComponent,
        canActivate: [AuthenticationGuard, ManagementGuard],
        data: {
          title: "Users"
        },
      },
      {
        path: "levels",
        component: LevelsComponent,
        canActivate: [AuthenticationGuard, ManagementGuard],
        data: {
          title: "Levels"
        },
      },
      {
        path: "forbidden",
        component: ForbiddenPageComponent,
        canActivate: [AuthenticationGuard],
        data: {
          title: "Forbidden"
        },
      },
      {
        path: "404",
        component: NotFoundPageComponent,
        canActivate: [AuthenticationGuard],
        data: {
          title: "404"
        },
      }
    ]
  },
  {
    path: "login",
    component: LoginComponent,
    data: {
      title: "Sign in"
    },
  },
  {
    path: "register",
    component: RegisterComponent,
    data: {
      title: "Sign up"
    },
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
