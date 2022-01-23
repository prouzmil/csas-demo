import { Pagination } from "./pagination";

/** Response s informacemi o strankovani */
export interface PagedResponse<T> {
  /** Informace o strankovani */
  pagination: Pagination;
  /** Polozky */
  items: T[];
}
