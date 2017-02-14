import { Component, AfterViewInit, ElementRef } from '@angular/core';
import 'rxjs/add/operator/toPromise';
import { ListService } from '../../service/list/list.service';
import { ListItem } from '../../model/list-item';

@Component({
    selector: 'liferay-angular-portlet',
    templateUrl: 'main.component.html',
    styleUrls: ['main.component.scss']
})
export class MainComponent implements AfterViewInit {
    static API_URL = '';
    listItems: ListItem[];
    text: string;

    constructor(elementRef: ElementRef, private listService: ListService) {
        MainComponent.API_URL = elementRef.nativeElement.getAttribute('api-url');
    }

    ngAfterViewInit(): void {
        this.listService.getList().then((list) => {
            this.listItems = list;
        });
    }

    public static getApiUrl(resource: string): string {
        return this.API_URL + resource;
    }
}
