
package cz.csas.demo;

import lombok.Value;

import java.util.List;

/**
 * Response s informacemi o strankovani
 * @param <T> typ polozek na strance
 */
@Value
public class PagedResponse<T> {
    /** Informace o strankovani */
    Pagination pagination;
    /** Polozky */
    List<T> items;
}
