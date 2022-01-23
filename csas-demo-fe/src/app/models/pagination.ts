/** Informace o strankovani */
export interface Pagination {
  /** Aktualni stranka */
  pageNumber: number;
  /** Celkovy pocet stranek */
  pageCount: number;
  /** Velikost stranky */
  pageSize: number;
  /** Celkovy pocet prvku */
  totalItemCount: number;
}
