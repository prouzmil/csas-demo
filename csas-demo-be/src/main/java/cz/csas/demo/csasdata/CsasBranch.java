
package cz.csas.demo.csasdata;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Pobocka CSAS
 */
@Getter
@NoArgsConstructor
public class CsasBranch {
    /** ID pobocky */
    private long id;
    /** Souradnice */
    private CsasLocation location;
    /** Nazev */
    private String name;
    /** Adresa */
    private String address;
    /** Mesto */
    private String city;
    /** PSC */
    private String postCode;
}
