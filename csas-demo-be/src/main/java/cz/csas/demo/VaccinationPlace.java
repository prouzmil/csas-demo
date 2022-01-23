package cz.csas.demo;

import cz.csas.demo.common.Coordinates2D;
import cz.csas.demo.mzcrdata.MzcrVaccinationPlace;
import lombok.Builder;
import lombok.Value;

/**
 * Ockovaci misto
 */
@Value
@Builder
public class VaccinationPlace implements Coordinates2D {
    /** ID ockovaciho mista */
    String id;
    /** Nazev */
    String name;
    /** Adresa */
    String address;
    /** Souradnice */
    Coordinates coordinates;

    public static VaccinationPlace from(MzcrVaccinationPlace mzcrVaccinationPlace) {
        return VaccinationPlace.builder()
                .id(mzcrVaccinationPlace.getId())
                .name(mzcrVaccinationPlace.getName())
                .address(mzcrVaccinationPlace.getAddress())
                .coordinates(
                        mzcrVaccinationPlace.getLatitude() != null && mzcrVaccinationPlace.getLongitude() != null
                                ? new Coordinates(mzcrVaccinationPlace.getLatitude(), mzcrVaccinationPlace.getLongitude())
                                : null
                )
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
