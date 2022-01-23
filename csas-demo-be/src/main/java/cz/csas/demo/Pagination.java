package cz.csas.demo;

import lombok.Builder;
import lombok.Value;

/**
 * Informace o strankovani a poctu polozek
 */
@Value
@Builder
public class Pagination {
    /** Aktualni stranka */
    int pageNumber;
    /** Celkovy pocet stranek */
    int pageCount;
    /** Velikost stranky */
    int pageSize;
    /** Celkovy pocet polozek */
    int totalItemCount;
}
