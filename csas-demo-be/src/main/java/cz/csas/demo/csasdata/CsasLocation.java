
package cz.csas.demo.csasdata;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Souradnice
 */
@Getter
@NoArgsConstructor
public class CsasLocation {
    /** Zemepisna delka */
    private double lat;
    /** Zemepisna sirka */
    private double lng;
}
