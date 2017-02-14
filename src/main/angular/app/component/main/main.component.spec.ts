import {} from 'jasmine';
import { TestBed, async, inject, tick, fakeAsync } from '@angular/core/testing';
import { HttpModule, Response, ResponseOptions } from '@angular/http';
import { MockBackend } from '@angular/http/testing';
import { MainComponent } from './main.component';
import { getMock } from '../../../testHelper';
import { ListService } from '../../service/list/list.service';
import { FormsModule } from '@angular/forms';
import { ListItem } from '../../model/list-item';
import { ElementRef } from '@angular/core';

describe('MainComponent', () => {
    beforeEach(() => {
        TestBed.configureTestingModule({
            declarations: [
                MainComponent
            ],
            imports: [
                FormsModule
            ],
            providers: [
                {provide: ListService, useValue: getMock(ListService)},
                {provide: ElementRef, useValue: getMock(ElementRef)}
            ]
        });
        TestBed.compileComponents();
    });

    it('should create the app', async(() => {
        let fixture = TestBed.createComponent(MainComponent);
        let app = fixture.debugElement.componentInstance;
        expect(app).toBeTruthy();
    }));

    it('should not render the list items', fakeAsync(inject([ListService], (listService) => {
        listService.getList.and.callFake(() => {
            return Promise.resolve(null);
        });

        let fixture = TestBed.createComponent(MainComponent);
        tick();
        fixture.detectChanges();
        tick();
        fixture.detectChanges();

        let compiled = fixture.debugElement.nativeElement;
        expect(compiled.querySelector('ul')).toBeNull();
    })));

    it('should render the correct list items', fakeAsync(inject([ListService], (listService) => {
        let list: ListItem[] = [
            {id: 1, text: 'First'},
            {id: 2, text: 'Second'},
            {id: 3, text: 'Third'}
        ];

        listService.getList.and.callFake(() => {
            return Promise.resolve(list);
        });

        let fixture = TestBed.createComponent(MainComponent);
        tick();
        fixture.detectChanges();
        tick();
        fixture.detectChanges();

        let compiled = fixture.debugElement.nativeElement;
        let listElement = compiled.querySelector('ul');
        expect(listElement).toBeDefined();
        let listItems = listElement.querySelectorAll('li');
        expect(listItems[0].textContent).toBe('1: First');
        expect(listItems[1].textContent).toBe('2: Second');
        expect(listItems[2].textContent).toBe('3: Third');
    })));
});
