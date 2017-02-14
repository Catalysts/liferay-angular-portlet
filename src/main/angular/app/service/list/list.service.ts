import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { MainComponent } from '../../component/main/main.component';
import { ListItem } from '../../model/list-item';

@Injectable()
export class ListService {
    constructor(private http: Http) {

    }

    getList(): Promise<ListItem[]> {
        return this.http.get(MainComponent.getApiUrl('list'))
            .toPromise().then((response) => <ListItem[]>response.json())
            .catch((error) => Promise.reject(error.json()));
    }
}
