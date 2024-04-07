import {Component, inject, Input, OnDestroy} from '@angular/core';
import {Album} from "../shared/models/album.model";
import {AlbumService} from "../shared/services/album.service";
import {Subject, takeUntil} from "rxjs";
import {ServiceResponse} from "../shared/models/service.response.model";
import {AlbumStorage} from "../shared/storage/album-storage";
import {Genre} from "../shared/models/genre.enum";

@Component({
  selector: 'app-album',
  templateUrl: './album.component.html',
  styleUrls: ['./album.component.scss']
})
export class AlbumComponent implements OnDestroy {

  private albumStorage: AlbumStorage = inject(AlbumStorage);
  private _onDestroy: Subject<void> = new Subject<void>();

  @Input()
  public album!: Album;

  public ngOnDestroy(): void {
    this._onDestroy.next();
    this._onDestroy.complete();
  }

  get averageRating(): string {
    if (this.album.ratings.length === 0) return 'no rating';

    const sum: number = this.album.ratings.reduce((accumulator: number, currentRating: number) => accumulator + currentRating, 0);
    return `rating ${(sum / this.album.ratings.length).toFixed(1)}`;
  }

  public deleteAlbum(): void {
    this.albumStorage.deleteAlbumById(this.album.id);
  }

  get genre(): string {
    return Genre.EDM
  }
}
