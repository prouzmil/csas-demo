import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { DemoService } from "./demo.service";
import { HttpClientModule } from "@angular/common/http";
import { BranchesListComponent } from './branches-list/branches-list.component';
import { PaginationComponent } from './pagination/pagination.component';
import { PaginationControlsComponent } from './pagination/pagination-controls/pagination-controls.component';

@NgModule({
  declarations: [
    AppComponent,
    BranchesListComponent,
    PaginationComponent,
    PaginationControlsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [DemoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
