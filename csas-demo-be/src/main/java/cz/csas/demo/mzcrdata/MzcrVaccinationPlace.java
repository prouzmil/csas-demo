package cz.csas.demo.mzcrdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Ockovaci misto
 */
@Getter
@NoArgsConstructor
public class MzcrVaccinationPlace {
    /** ID ockovaciho mista */
    @JsonProperty("ockovaci_misto_id")
    private String id;
    /** Nazev ockovaciho mista */
    @JsonProperty("ockovaci_misto_nazev")
    private String name;
    /** Adresa */
    @JsonProperty("ockovaci_misto_adresa")
    private String address;
    /** Zemepisna sirka */
    @JsonProperty("latitude")
    private Double latitude;
    /** Zemepisna delka */
    @JsonProperty("longitude")
    private Double longitude;
}
