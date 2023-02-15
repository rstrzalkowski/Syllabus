import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './components/login/login.component';
import {ThemeSwitchComponent} from './components/utilities/theme-switch/theme-switch.component';
import {RegisterComponent} from './components/register/register.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {HomeComponent} from './components/home/home.component';
import {ProfileComponent} from './components/profile/profile.component';
import {JwtInterceptor} from "./interceptors/jwt.interceptor";
import {SettingsComponent} from './components/settings/settings.component';
import {AlertComponent} from './components/utilities/alert/alert.component';
import {RealisationComponent} from './components/realisation/realisation.component';
import {ForbiddenPageComponent} from './components/utilities/forbidden-page/forbidden-page.component';
import {NotFoundPageComponent} from './components/utilities/not-found-page/not-found-page.component';
import {LoadingComponent} from './components/utilities/loading/loading.component';
import {AverageGradeComponent} from './components/realisation/average-grade/average-grade.component';
import {PostsComponent} from './components/realisation/posts/posts.component';
import {IncomingActivitiesComponent} from './components/realisation/incoming-activities/incoming-activities.component';
import {GradesComponent} from "./components/grades/grades.component";
import {FooterComponent} from './components/utilities/footer/footer.component';
import {CreatePostComponent} from './components/create-post/create-post.component';
import {CreateActivityComponent} from './components/create-activity/create-activity.component';
import {ActivitiesComponent} from "./components/activities/activities.component";
import {EditActivityComponent} from './components/edit-activity/edit-activity.component';
import {EditPostComponent} from './components/edit-post/edit-post.component';
import {GradeStudentsComponent} from './components/grade-students/grade-students.component';
import {RealisationsComponent} from "./components/manage-school/realisations/realisations.component";
import {ClassesComponent} from "./components/manage-school/classes/classes.component";
import {UsersComponent} from "./components/manage-school/users/users.component";
import {LevelsComponent} from "./components/manage-school/levels/levels.component";
import {SubjectsComponent} from "./components/manage-school/subjects/subjects.component";
import {
  ManageSchoolSidebarComponent
} from "./components/manage-school/manage-school-sidebar/manage-school-sidebar.component";
import {EditSubjectComponent} from './components/manage-school/subjects/edit-subject/edit-subject.component';
import {CreateSubjectComponent} from './components/manage-school/subjects/create-subject/create-subject.component';


@NgModule({
  bootstrap: [AppComponent],
  declarations: [
    AppComponent,
    LoginComponent,
    ThemeSwitchComponent,
    RegisterComponent,
    HomeComponent,
    HomeComponent,
    ProfileComponent,
    SettingsComponent,
    AlertComponent,
    RealisationComponent,
    ForbiddenPageComponent,
    NotFoundPageComponent,
    LoadingComponent,
    AverageGradeComponent,
    PostsComponent,
    IncomingActivitiesComponent,
    GradesComponent,
    FooterComponent,
    CreatePostComponent,
    CreateActivityComponent,
    ActivitiesComponent,
    EditActivityComponent,
    EditPostComponent,
    GradeStudentsComponent,
    ManageSchoolSidebarComponent,
    SubjectsComponent,
    RealisationsComponent,
    ClassesComponent,
    UsersComponent,
    LevelsComponent,
    EditSubjectComponent,
    CreateSubjectComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }]
})
export class AppModule {
}
