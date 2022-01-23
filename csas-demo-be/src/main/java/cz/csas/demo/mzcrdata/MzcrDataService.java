package cz.csas.demo.mzcrdata;

import cz.csas.demo.VaccinationPlace;
import cz.csas.demo.common.kdtree.KDTree;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Sluzba pro volani MZCR API
 */
@Service
@Slf4j
public class MzcrDataService {
    /** API key */
    @Value("${apiKeys.mzcr}")
    private String apiKey;

    /** API base URL */
    private final String apiUrl = "https://onemocneni-aktualne.mzcr.cz/api/v3";

    private final WebClient webClient = WebClient.builder()
            .baseUrl(apiUrl)
            .build();

    /**
     * Nacte kompletni seznam ockovacich mist a sestavi z nich KD strom podle jejich souradnic
     * @return KD strom ockovacich mist
     */
    @Cacheable("vaccinationPlacesCache")
    public KDTree<VaccinationPlace> getVaccinationPlacesKDTree() {
        // zjisteni poctu ockovacich mist
        MzcrPagedResponse<MzcrVaccinationPlace> response = getVaccinationPlaces(0);
        // nacteni vsech ockovacich mist
        response = getVaccinationPlaces(response.getTotalItems());
        // konverze objektu
        List<VaccinationPlace> vaccinationPlaces = response.getItems().stream()
                .map(VaccinationPlace::from)
                .collect(Collectors.toList());
        return new KDTree<>(vaccinationPlaces);
    }

    /**
     * Nacte seznam ockovacich mist
     * @param itemsCount pocet zaznamu, ktery se ma nacist
     * @return ockovaci mista
     */
    public MzcrPagedResponse<MzcrVaccinationPlace> getVaccinationPlaces(int itemsCount) {
        Mono<MzcrPagedResponse<MzcrVaccinationPlace>> pagedResponseMono = webClient.get()
                .uri(uriBuilder -> addApiToken(uriBuilder)
                        .path("/prehled-ockovacich-mist")
                        .queryParam("itemsPerPage", itemsCount)
                        .build()
                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
        try {
            return pagedResponseMono.block();
        } catch (Exception e) {
            log.error("Error fetching response", e);
            throw e;
        }
    }

    private UriBuilder addApiToken(UriBuilder uriBuilder) {
        return uriBuilder.queryParam("apiToken", apiKey);
    }


}
