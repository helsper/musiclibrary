import {Genre} from "./genre.enum";

export interface AlbumForm {
  id?: number;
  name?: string;
  artist?: string;
  releaseDate?: Date;
  numberOfSongs?: number;
  genre?: Genre;
  description?: string;
  ratings?: number[];
  image?: string;
}
