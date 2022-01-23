package cz.csas.demo.csasdata;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

/**
 * Sluzba pro volani CSAS API
 */
@Service
@Slf4j
public class CsasDataService {

    /** API key */
    @Value("${apiKeys.csas}")
    private String apiKey;

    /** API base URL */
    private final String apiUrl = "https://www.csas.cz/webapi/api/v3/places";

    private WebClient webClient;

    @PostConstruct
    public void init() {
        webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader("WEB-API-key", apiKey)
                .build();
    }

    /**
     * @param page stranka
     * @param size pocet zaznamu na strance
     * @return seznam pobocek
     */
    public CsasPagedResponse<CsasBranch> getBranches(int page, int size) {
        log.info("Calling " + apiUrl + "/branches");
        Mono<CsasPagedResponse<CsasBranch>> pagedResponseMono = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/branches")
                        .queryParam("page", page - 1)
                        .queryParam("size", size)
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
}
