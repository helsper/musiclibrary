import {Component, inject} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Genre} from "../shared/models/genre.enum";
import {AlbumForm} from "../shared/models/albumForm.model";
import {AlbumStorage} from "../shared/storage/album-storage";
import {AlbumService} from "../shared/services/album.service";

@Component({
  selector: 'app-create-album',
  templateUrl: './create-album.component.html',
  styleUrls: ['./create-album.component.scss']
})
export class CreateAlbumComponent {

  private formBuilder: FormBuilder = inject(FormBuilder);
  private albumStorage: AlbumStorage = inject(AlbumStorage);
  private albumFormModel: AlbumForm = {};

  public genres: string[] = Object.values(Genre);
  public ratings: number[] = [1,2,3,4,5];
  public albumForm: FormGroup = this.formBuilder.group({
    name: ['', Validators.required],
    artist: ['', Validators.required],
    releaseDate: [null, Validators.required],
    numberOfSongs: [null, Validators.required],
    genre: ['', Validators.required],
    description: [''],
    rating:[null],
    image: [null]
  });

  public createAlbum(): void {
    this.albumFormModel.name = this.albumForm.get('name')?.value ?? '' ;
    this.albumFormModel.artist = this.albumForm.get('artist')?.value ?? '';
    this.albumFormModel.releaseDate = this.albumForm.get('releaseDate')?.value ?? '';
    this.albumFormModel.numberOfSongs = this.albumForm.get('numberOfSongs')?.value ?? null;
    this.albumFormModel.genre = this.albumForm.get('genre')?.value ?? '';
    this.albumFormModel.description = this.albumForm.get('description')?.value ?? '';
    this.albumFormModel.ratings = [this.albumForm.get('rating')?.value] ?? null;

    this.albumStorage.createAlbum(this.albumFormModel);
  }

  public getValueofGenre(genre: string) {
    const genreKeys = Object.keys(Genre).filter(key => typeof Genre[key as keyof typeof Genre] === 'string') as (keyof typeof Genre)[];
    for (const key of genreKeys) {
      if (Genre[key] === genre) {
        return key;
      }
    }
    return '';
  }
}
