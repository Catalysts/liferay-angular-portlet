import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { MainComponent } from './component/main/main.component';
import { HttpModule } from '@angular/http';
import { ListService } from './service/list/list.service';

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule
    ],
    declarations: [
        MainComponent
    ],
    entryComponents: [],
    providers: [
        ListService
    ],
    bootstrap: [MainComponent]
})
export class AppModule {
}
