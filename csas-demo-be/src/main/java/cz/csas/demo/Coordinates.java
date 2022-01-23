
package cz.csas.demo;

import cz.csas.demo.common.Coordinates2D;
import cz.csas.demo.csasdata.CsasLocation;
import lombok.Value;

/**
 * Geograficke souradnice
 */
@Value
public class Coordinates implements Coordinates2D {
    /** Zemepisna delka */
    double lat;
    /** Zemepisna sirka */
    double lng;

    @Override
    public double getX() {
        return lat;
    }

    @Override
    public double getY() {
        return lng;
    }

    public static Coordinates from(CsasLocation csasLocation) {
        return new Coordinates(csasLocation.getLat(), csasLocation.getLng());
    }
}
