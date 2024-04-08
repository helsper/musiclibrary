import {Component, inject} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Genre} from "../shared/models/genre.enum";

@Component({
  selector: 'app-create-album',
  templateUrl: './create-album.component.html',
  styleUrls: ['./create-album.component.scss']
})
export class CreateAlbumComponent {

  private formBuilder: FormBuilder = inject(FormBuilder);

  public genres: string[] = Object.values(Genre);
  public ratings: number[] = [1,2,3,4,5];
  public albumForm: FormGroup = this.formBuilder.group({
    name: ['', Validators.required],
    artist: ['', Validators.required],
    releaseDate: [null, Validators.required],
    numberOfSongs: [null, Validators.required],
    genre: ['', Validators.required],
    description: [''],
    image: ''
  });

  public createAlbum(): void {
    //Todo create request
    // this.albumForm.get('name')?.value ?? '';
    // this.albumForm.get('artist')?.value ?? '';
    // this.albumForm.get('releaseDate')?.value ?? '';
    // this.albumForm.get('numberOfSongs')?.value ?? '';
    // this.albumForm.get('genre')?.value ?? '';
    // this.albumForm.get('description')?.value ?? '';
    // this.album.ratings = this.albumForm.get('ratings')?.value ?? '';
    // this.album.image = this.albumForm.get('image')?.value ?? '';
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
