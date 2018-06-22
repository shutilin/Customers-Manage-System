import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class HttpClientService {



    private headers: HttpHeaders = new HttpHeaders({
        'Content-Type': 'application/json'
    });

    constructor(private http: HttpClient) {
    }

    /**
     * GET-запрос
     * @param {string} url - url запроса
     * @param {Function} successCallback - функция-обработчик ответа
     * @param params - параметры запроса
     */
    public callGET(url: string, successCallback: Function, params?: any): void {

        this.http.get(url, {headers: this.headers, params: params})
            .subscribe((res: any) => {
                successCallback(res);
            }, (err) => {
                // TODO сделть это модальными окнами
                console.log(err);
            });
    }

    /**
     * POST - запрос
     * @param {string} url - url запроса
     * @param body - тело запроса
     * @param {Function} successCallBack - функция-обработчик ответа
     * @param params - параметры запроса
     */
    public callPOST(url: string, body: any, successCallBack: Function, params?: any): void {

        this.http.post(url, body, {headers: this.headers, params: params})
            .subscribe((res: any) => {
                successCallBack(res);
            }, (err) => {
                // TODO сделть это модальными окнами
                console.log(err);
            });
    }

    public callDELETE(url: string, successCallback: Function): void {
        this.http.delete(url)
            .subscribe((res: any) => {
                successCallback(res);
            }, (err) => {
                console.log(err);
            });
    }

    public callPUT(url: string, body: any, successCallback: Function): void {
        this.http.put(url, body)
            .subscribe((res: any) => {
                successCallback(res);
            }, (err) => {
                console.log(err);
            });
    }
}
