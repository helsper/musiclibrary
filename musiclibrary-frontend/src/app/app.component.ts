import {Component, inject, OnDestroy, OnInit} from '@angular/core';
import {AlbumService} from "./shared/services/album.service";
import {Album} from "./shared/models/album.model";
import {Observable, of, Subject, takeUntil, tap} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  private albumService: AlbumService = inject(AlbumService);

  public albums: Observable<Album[]> = this.albumService.getAllAlbums();
  public readonly title = 'musiclibrary-frontend';

}
