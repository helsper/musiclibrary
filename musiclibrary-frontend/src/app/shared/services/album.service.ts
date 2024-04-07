import {inject, Injectable} from "@angular/core";
import {environment} from "../../../environments/environment";
import {RestService} from "./rest.service";
import {Observable} from "rxjs";
import {Album} from "../models/album.model";
import {ServiceResponse} from "../models/service.response.model";

@Injectable({
  providedIn: 'root'
})
export class AlbumService {

  private readonly restService: RestService = inject(RestService);

  private readonly serviceUrl: string = environment.ALBUM_SERVICE_URL;
  private readonly albums: string = 'albums';

  // Todo future: maybe implement different sorting types
  public getAllAlbums(): Observable<Album[]> {
    return this.restService.get<Album[]>(`${this.serviceUrl}${this.albums}`);
  }

  public deleteAlbumById(id: number): Observable<ServiceResponse> {
    return this.restService.delete(`${this.serviceUrl}${this.albums}/${id}`);
  }
}
