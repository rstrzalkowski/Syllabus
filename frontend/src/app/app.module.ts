import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './components/login/login.component';
import {ThemeSwitchComponent} from './components/theme-switch/theme-switch.component';
import {RegisterComponent} from './components/register/register.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {HomeComponent} from './components/home/home.component';
import {ProfileComponent} from './components/profile/profile.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ThemeSwitchComponent,
    RegisterComponent,
    HomeComponent,
    HomeComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
