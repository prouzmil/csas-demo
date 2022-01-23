package cz.csas.demo.mzcrdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Response s informacemi o strankovani
 * @param <T> typ polozek na strance
 */
@Getter
@NoArgsConstructor
public class MzcrPagedResponse<T> {
    /** Celkovy pocet polozek */
    @JsonProperty("hydra:totalItems")
    private int totalItems;
    /** Polozky na strance */
    @JsonProperty("hydra:member")
    private List<T> items;
}
