import {Component, inject, OnDestroy, OnInit} from '@angular/core';
import {Album} from "./shared/models/album.model";
import {Subject, takeUntil} from "rxjs";
import {AlbumStorage} from "./shared/storage/album-storage";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {

  private albumStorage: AlbumStorage = inject(AlbumStorage);
  private unsubscribe$: Subject<void> = new Subject<void>();

  public readonly title = 'musiclibrary-frontend';
  public albums: Album[] = [];
  public showIcon: boolean = true;

  public ngOnInit(): void {
    this.getAllAlbums();
  }

  public ngOnDestroy(): void {
    this.unsubscribe$.next();
  }

  public getAllAlbums(): void {
    this.albumStorage.getAllAlbums();

    this.albumStorage.subscribeAlbums()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((albums: Album[]) => {
        this.albums = albums;
      });
  }

  public toggleShow() {
    this.showIcon = !this.showIcon;
  }
}
