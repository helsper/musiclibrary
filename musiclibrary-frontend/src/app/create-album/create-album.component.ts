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
  public albumForm: FormGroup = this.formBuilder.group({
      name: ['', Validators.required],
      artist: ['', Validators.required],
      releaseDate: [null, Validators.required],
      numberOfSongs: [null, Validators.required],
      genre: ['', Validators.required],
      description: ['']
  });



  public createAlbum(): void {

  }

  protected readonly Genre = Genre;
}
