import {Component, EventEmitter, inject, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Genre} from "../shared/models/genre.enum";
import {AlbumStorage} from "../shared/storage/album-storage";
import {Album} from "../shared/models/album.model";

@Component({
  selector: 'app-update-album',
  templateUrl: './update-album.component.html',
  styleUrls: ['./update-album.component.scss']
})
export class UpdateAlbumComponent implements OnInit {

  @Input()
  album!: Album;

  @Output()
  abortUpdateEvent: EventEmitter<void> = new EventEmitter<void>();

  private formBuilder: FormBuilder = inject(FormBuilder);
  private albumStorage: AlbumStorage = inject(AlbumStorage);

  public genres: string[] = Object.values(Genre);
  public ratings: number[] = [1,2,3,4,5];
  public albumForm!: FormGroup;

  public ngOnInit(): void {
    this.albumForm = this.formBuilder.group({
      name: [this.album.name, Validators.required],
      artist: [this.album.artist, Validators.required],
      releaseDate: [this.album.releaseDate, Validators.required],
      numberOfSongs: [this.album.numberOfSongs, Validators.required],
      genre: [this.album.genre, Validators.required],
      description: [this.album.description],
      image: [this.album.image]
    });
  }

  public updateAlbum(): void {
    this.album.name = this.albumForm.get('name')?.value ?? '' ;
    this.album.artist = this.albumForm.get('artist')?.value ?? '';
    this.album.releaseDate = this.albumForm.get('releaseDate')?.value ?? '';
    this.album.numberOfSongs = this.albumForm.get('numberOfSongs')?.value ?? null;
    this.album.genre = this.albumForm.get('genre')?.value ?? '';
    this.album.description = this.albumForm.get('description')?.value ?? '';
    this.album.image = this.albumForm.get('image')?.value ?? null;

    this.albumStorage.updateAlbumById(this.album);

    this.abortUpdate();
    this.albumForm.reset();
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

  public onFileSelected(event: Event): void {
    const inputElement = event.target as HTMLInputElement;
    if (inputElement.files && inputElement.files.length > 0 && inputElement.files[0]) {
      this.getBase64(inputElement.files[0]).then((base64String: string) => {
        this.albumForm.get('image')?.setValue(base64String.replace("data:image/jpeg;base64,", ""));
      });
    }
  }

  public abortUpdate(): void {
    this.abortUpdateEvent.emit();
  }

  private async getBase64(file: File): Promise<string> {
    return new Promise((resolve, reject) => {
      const reader: FileReader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => resolve(reader.result as string);
      reader.onerror = error => reject(error);
    });
  }
}
