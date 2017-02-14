import { TestBed, inject, tick, fakeAsync } from '@angular/core/testing';
import { Http } from '@angular/http';
import { getMock } from '../../../testHelper';
import { ListService } from './list.service';
import { MainComponent } from '../../component/main/main.component';

describe('ListService', () => {
    beforeEach(() => {
        TestBed.configureTestingModule({
            declarations: [],
            imports: [],
            providers: [
                {provide: Http, useValue: getMock(Http)},
                ListService
            ]
        });
        TestBed.compileComponents();
    });

    it('should get the list', fakeAsync(inject([ListService, Http], (listService, http) => {
        const API_URL = 'http://test/api/';

        spyOn(MainComponent, 'getApiUrl').and.returnValue(API_URL);
        http.get.and.returnValue({
            toPromise: () => {
                return Promise.resolve({
                    json: () => {
                        return [
                            {
                                id: 0,
                                text: 'Item1'
                            }
                        ];
                    }
                });
            }
        });

        listService.getList().then((items) => {
            expect(items[0].id).toBe(0);
            expect(items[0].text).toBe('Item1');
        });

        tick();

        expect(http.get).toHaveBeenCalledWith(API_URL);
        expect(MainComponent.getApiUrl).toHaveBeenCalledWith('list');
    })));

    it('should throw an error', fakeAsync(inject([ListService, Http], (listService, http) => {
        const API_URL = 'http://test/api/';
        const ERROR_MESSAGE = 'errorText';

        spyOn(MainComponent, 'getApiUrl').and.returnValue(API_URL);
        http.get.and.returnValue({
            toPromise: () => {
                return Promise.reject({
                    json: () => {
                        return {
                            message: ERROR_MESSAGE
                        };
                    }
                });
            }
        });

        listService.getList().catch((error) => {
            expect(error.message).toBe(ERROR_MESSAGE);
        });

        tick();

        expect(http.get).toHaveBeenCalledWith(API_URL);
        expect(MainComponent.getApiUrl).toHaveBeenCalledWith('list');
    })));
});
