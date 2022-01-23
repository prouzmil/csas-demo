import { Coordinates } from "./coordinates";

/** Ockovaci centrum */
export interface VaccinationPlace {
  /** ID ockovaciho centra */
  id: string;
  /** Nazev ockovaciho centra */
  name: string;
  /** Adresa */
  address: string;
  /** Souradnice */
  coordinates: Coordinates;
}
