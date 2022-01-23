package cz.csas.demo;

import cz.csas.demo.common.Coordinates2D;
import cz.csas.demo.csasdata.CsasBranch;
import lombok.Builder;
import lombok.Data;

/**
 * Bankovni pobocka s informaci o nejblizsim ockovacim mistu
 */
@Data
@Builder
public class Branch implements Coordinates2D {
    /** ID pobocky */
    private long id;
    /** Nazev pobocky */
    private String name;
    /** Adresa */
    private String address;
    /** Souradnice */
    private Coordinates coordinates;
    /** Nejblizsi ockovaci misto */
    private VaccinationPlace nearestVaccinationPlace;

    public static Branch from(CsasBranch branch) {
        return Branch.builder()
                .id(branch.getId())
                .name(branch.getName())
                .address(branch.getAddress() + ", " + branch.getCity() + ", " + branch.getPostCode())
                .coordinates(branch.getLocation() != null ? Coordinates.from(branch.getLocation()) : null)
                .build();
    }

    @Override
    public double getX() {
        return coordinates != null ? coordinates.getLng() : 0;
    }

    @Override
    public double getY() {
        return coordinates != null ? coordinates.getLat() : 0;
    }
}
