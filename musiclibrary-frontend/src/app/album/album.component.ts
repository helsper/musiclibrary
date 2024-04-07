import {Component, Input} from '@angular/core';
import {Album} from "../shared/models/album.model";

@Component({
  selector: 'app-album',
  templateUrl: './album.component.html',
  styleUrls: ['./album.component.scss']
})
export class AlbumComponent {

  @Input()
  public album!: Album;

  get averageRating(): string {
    if (this.album.ratings.length === 0) return 'no rating';

    const sum: number = this.album.ratings.reduce((accumulator: number, currentRating: number) => accumulator + currentRating, 0);
    return `rating ${(sum / this.album.ratings.length).toFixed(1)}`;
  }
}
