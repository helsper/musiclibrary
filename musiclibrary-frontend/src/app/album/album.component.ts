import {Component, inject, Input, OnDestroy} from '@angular/core';
import {Album} from "../shared/models/album.model";
import {Subject} from "rxjs";
import {AlbumStorage} from "../shared/storage/album-storage";

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
  public showUpdateModal: boolean = false;

  public ngOnDestroy(): void {
    this._onDestroy.next();
    this._onDestroy.complete();
  }

  get genre(): string {
    return this.album.genre;
  }

  get averageRating(): string {
    if (this.album.ratings.length === 0) return 'no rating';

    const sum: number = this.album.ratings.reduce((accumulator: number, currentRating: number) => accumulator + currentRating, 0);
    return `rating ${(sum / this.album.ratings.length).toFixed(1)}`;
  }

  get detailedRating(): string {
    if (this.album.ratings.length === 0) return 'no rating';

    const sum: number = this.album.ratings.reduce((accumulator: number, currentRating: number) => accumulator + currentRating, 0);
    return `${this.album.ratings.length} rating(s) with average of ${(sum / this.album.ratings.length).toFixed(1)}`;
  }

  public deleteAlbum(): void {
    this.albumStorage.deleteAlbumById(this.album.id);
  }

  public toggleUpdateModal(): void {
    this.showUpdateModal = !this.showUpdateModal
  }

  public rateAlbum(number: number): void {
    this.album.ratings.push(number);
    this.albumStorage.updateAlbumById(this.album);
  }
}
