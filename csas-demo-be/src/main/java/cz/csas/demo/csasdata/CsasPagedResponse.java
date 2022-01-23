
package cz.csas.demo.csasdata;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Response s informacemi o strankovani
 * @param <T> typ polozek na strance
 */
@Getter
@NoArgsConstructor
public class CsasPagedResponse<T> {
    /** Celkovy pocet stranek */
    private int pageCount;
    /** Celkovy pocet polozek */
    private int totalItemCount;
    /** Polozky na strance */
    private List<T> items = new ArrayList<>();
}
