import { Coordinates } from "./coordinates";
import { VaccinationPlace } from "./vaccination-place";

/** Pobocka */
export interface Branch {
  /** ID pobocky*/
  id: number;
  /** Nazev pobocky */
  name: string;
  /** Adresa */
  address: string;
  /** Souradnice */
  coordinates: Coordinates;
  /** Nejblizsi ockovaci centrum */
  nearestVaccinationPlace: VaccinationPlace;
}
