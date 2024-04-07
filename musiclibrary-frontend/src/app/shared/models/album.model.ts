import {Genre} from "./genre.enum";

export interface Album {
  id: number;
  created: Date;
  updated: Date;
  name: string;
  artist: string;
  releaseDate: Date;
  numberOfSongs: number;
  genre: Genre;
  description: string;
  ratings: number[];
  image: string;
}
