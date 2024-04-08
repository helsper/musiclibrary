import {inject, Injectable, OnDestroy} from "@angular/core";
import {Observable, ReplaySubject, Subject, take, takeUntil} from "rxjs";
import {Album} from "../models/album.model";
import {AlbumService} from "../services/album.service";
import {ServiceResponse} from "../models/service.response.model";
import {AlbumForm} from "../models/albumForm.model";

@Injectable({
  providedIn: 'root'
})
export class AlbumStorage implements OnDestroy {

  private albumService: AlbumService = inject(AlbumService);
  private $albums: ReplaySubject<Album[]> = new ReplaySubject<Album[]>(1);
  private albums: Album[] = [];

  private _onDestroy: Subject<void> = new Subject<void>();

  public ngOnDestroy(): void {
    this._onDestroy.next();
    this._onDestroy.complete();
  }

  public subscribeAlbums(): Subject<Album[]> {
    return this.$albums;
  }

  public deleteAlbumById(id: number): void {
    this.albumService.deleteAlbumById(id)
      .pipe(takeUntil(this._onDestroy))
      .subscribe((response: ServiceResponse) => {
        if (response.success) {
          this.removeAlbumFromArray(id);
          console.log(response.message);
        } else {
          console.log(response.message);
        }
      })
  }

  private removeAlbumFromArray(id: number) {
    for (let i = 0; i < this.albums.length; i++) {
      if (this.albums[i].id === id) {
        this.albums.splice(i, 1);
      }
    }

    this.$albums.next(this.albums);
  }

  public updateAlbums(newAlbums: Observable<Album[]>): void {
    newAlbums.pipe(take(1)).subscribe((newAlbums: Album[]) => {
      this.albums = newAlbums;
      this.$albums.next(this.albums);
    });
  }

  public getAllAlbums(): void {
    this.updateAlbums(this.albumService.getAllAlbums());
  }

  public createAlbum(albumForm: AlbumForm): void {

    this.albumService.createAlbum(albumForm)
      .pipe(takeUntil(this._onDestroy))
      .subscribe((newAlbum: Album) => this.addAlbumtoStorage(newAlbum));
  }

  private addAlbumtoStorage(album: Album): void {
    this.albums.push(album);
    this.$albums.next(this.albums);
  }
}
