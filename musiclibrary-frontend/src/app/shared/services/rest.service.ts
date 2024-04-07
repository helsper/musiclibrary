import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {catchError, Observable, OperatorFunction} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RestService {

  constructor(private http: HttpClient) {
  }

  public get<T>(url: string): Observable<T> {
    return this.http.get<T>(url).pipe(this.checkHttpResponseType());
  }

  public post<T>(url: string, body: unknown): Observable<T> {
    return this.http.post<T>(url, body).pipe(this.checkHttpResponseType());
  }

  public put<T>(url: string, body: unknown): Observable<T> {
    return this.http.put(url, body).pipe(this.checkHttpResponseType());
  }

  public delete<T>(url: string): Observable<T> {
    return this.http.delete(url).pipe(this.checkHttpResponseType());
  }

  private checkHttpResponseType(): OperatorFunction<any, any> {
    return catchError((err: unknown, caught: Observable<any>) => {

      if (!(err instanceof HttpErrorResponse)) {
        throw err;
      }

      if (err.status !== 403 && err.status !== 401) {
        throw err;
      }

      throw err;
    });
  }
}
