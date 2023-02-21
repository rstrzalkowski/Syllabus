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
import {OwnProfileComponent} from './components/own-profile/own-profile.component';
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
import {CreatePostComponent} from './components/posts/create-post/create-post.component';
import {CreateActivityComponent} from './components/activities/create-activity/create-activity.component';
import {ActivitiesComponent} from "./components/activities/activities.component";
import {EditActivityComponent} from './components/activities/edit-activity/edit-activity.component';
import {EditPostComponent} from './components/posts/edit-post/edit-post.component';
import {GradeStudentsComponent} from './components/activities/grade-students/grade-students.component';
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
import {CreateLevelComponent} from './components/manage-school/levels/create-level/create-level.component';
import {CreateClassComponent} from './components/manage-school/classes/create-class/create-class.component';
import {EditClassComponent} from './components/manage-school/classes/edit-class/edit-class.component';
import {GenerateCodeComponent} from './components/manage-school/users/generate-code/generate-code.component';
import {CodesComponent} from './components/manage-school/users/codes/codes.component';
import {
  CreateRealisationComponent
} from './components/manage-school/realisations/create-realisation/create-realisation.component';
import {
  EditRealisationComponent
} from './components/manage-school/realisations/edit-realisation/edit-realisation.component';
import {EditStudentsComponent} from './components/manage-school/classes/edit-students/edit-students.component';
import {DashboardComponent} from './components/dashboard/dashboard.component';
import {ImageUploadComponent} from './components/image-upload/image-upload.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';


@NgModule({
  bootstrap: [AppComponent],
  declarations: [
    AppComponent,
    LoginComponent,
    ThemeSwitchComponent,
    RegisterComponent,
    HomeComponent,
    HomeComponent,
    OwnProfileComponent,
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
    CreateLevelComponent,
    CreateClassComponent,
    EditClassComponent,
    GenerateCodeComponent,
    CodesComponent,
    CreateRealisationComponent,
    EditRealisationComponent,
    EditStudentsComponent,
    DashboardComponent,
    ImageUploadComponent,
    UserProfileComponent,
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
